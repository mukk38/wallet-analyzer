package tr.com.muskar.walletAnalyzer.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tr.com.muskar.walletAnalyzer.model.dto.EtherscanTxResponse;
import tr.com.muskar.walletAnalyzer.model.dto.EtherscanTxResult;
import tr.com.muskar.walletAnalyzer.model.dto.airdrop.AirdropDto;
import tr.com.muskar.walletAnalyzer.model.dto.token.EtherscanTokenTxResponse;
import tr.com.muskar.walletAnalyzer.model.dto.token.EtherscanTokenTxResult;
import tr.com.muskar.walletAnalyzer.model.dto.token.stats.TokenStatsDto;
import tr.com.muskar.walletAnalyzer.model.dto.wallet.WalletActivitySummaryDto;
import tr.com.muskar.walletAnalyzer.service.WalletService;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {


    private final WebClient etherscanWebClient;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Override
    public List<EtherscanTxResult> getTransactions(String address) {
        Mono<EtherscanTxResponse> responseMono = etherscanWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("module", "account")
                        .queryParam("action", "txlist")
                        .queryParam("address", address)
                        .queryParam("startblock", "0")
                        .queryParam("endblock", "99999999")
                        .queryParam("sort", "asc")
                        .queryParam("apikey", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(EtherscanTxResponse.class);

        EtherscanTxResponse response = responseMono.block(); // sync call for simplicity

        return response != null && "1".equals(response.getStatus()) ? response.getResult() : List.of();
    }

    @Override
    public List<EtherscanTokenTxResult> getTokenTransfers(String address) {
        Mono<EtherscanTokenTxResponse> responseMono = etherscanWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("module", "account")
                        .queryParam("action", "tokentx")
                        .queryParam("address", address)
                        .queryParam("startblock", "0")
                        .queryParam("endblock", "99999999")
                        .queryParam("sort", "asc")
                        .queryParam("apikey", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(EtherscanTokenTxResponse.class);

        EtherscanTokenTxResponse response = responseMono.block();

        return response != null && "1".equals(response.getStatus()) ? response.getResult() : List.of();
    }

    @Override
    public List<TokenStatsDto> getTokenStats(String address) {
        List<EtherscanTokenTxResult> transfers = getTokenTransfers(address);

        Map<String, TokenStatsDto> statsMap = new HashMap<>();

        for (EtherscanTokenTxResult tx : transfers) {
            String key = tx.getContractAddress();

            statsMap.compute(key, (k, existing) -> {
                if (existing == null) {
                    return new TokenStatsDto(
                            tx.getTokenSymbol(),
                            tx.getTokenName(),
                            tx.getContractAddress(),
                            1
                    );
                } else {
                    existing.setTransferCount(existing.getTransferCount() + 1);
                    return existing;
                }
            });
        }

        return statsMap.values().stream()
                .sorted(Comparator.comparingLong(TokenStatsDto::getTransferCount).reversed())
                .toList();
    }

    @Override
    public WalletActivitySummaryDto getWalletActivitySummary(String address) {
        List<EtherscanTxResult> txs = getTransactions(address);

        if (txs.isEmpty()) {
            return new WalletActivitySummaryDto(null, null, 0, 0, 0);
        }

        LocalDateTime first = txs.stream()
                .map(tx -> LocalDateTime.ofEpochSecond(Long.parseLong(tx.getTimeStamp()), 0, java.time.ZoneOffset.UTC))
                .min(LocalDateTime::compareTo)
                .orElse(null);

        LocalDateTime last = txs.stream()
                .map(tx -> LocalDateTime.ofEpochSecond(Long.parseLong(tx.getTimeStamp()), 0, java.time.ZoneOffset.UTC))
                .max(LocalDateTime::compareTo)
                .orElse(null);

        int totalTx = txs.size();

        double totalFeeEth = txs.stream()
                .mapToDouble(tx -> {
                    try {
                        long gasUsed = Long.parseLong(tx.getGas());
                        long gasPrice = Long.parseLong(tx.getGasPrice());
                        return (gasUsed * gasPrice) / 1e18;
                    } catch (NumberFormatException e) {
                        return 0.0;
                    }
                })
                .sum();

        double avgFeeEth = totalFeeEth / totalTx;

        return new WalletActivitySummaryDto(first, last, totalTx, totalFeeEth, avgFeeEth);
    }

    @Override
    public List<AirdropDto> detectAirdrops(String address) {
        List<EtherscanTokenTxResult> transfers = getTokenTransfers(address);
        Map<String, LocalDateTime> firstTimeMap = new HashMap<>();
        List<AirdropDto> airdrops = new ArrayList<>();

        for (EtherscanTokenTxResult tx : transfers) {
            boolean isIncoming = tx.getTo().equalsIgnoreCase(address);
            boolean isNotFromSelf = !tx.getFrom().equalsIgnoreCase(address);
            boolean isNotZero = new BigInteger(tx.getValue()).compareTo(BigInteger.ZERO) > 0;

            if (isIncoming && isNotFromSelf && isNotZero) {
                LocalDateTime txTime = LocalDateTime.ofEpochSecond(Long.parseLong(tx.getTimeStamp()), 0, java.time.ZoneOffset.UTC);
                String tokenKey = tx.getContractAddress().toLowerCase();

                if (!firstTimeMap.containsKey(tokenKey)) {
                    // İlk defa geldiği bu işlemse airdrop olarak değerlendir
                    firstTimeMap.put(tokenKey, txTime);

                    airdrops.add(new AirdropDto(
                            tx.getTokenSymbol(),
                            tx.getTokenName(),
                            tx.getContractAddress(),
                            tx.getFrom(),
                            txTime,
                            tx.getValue()
                    ));
                }
            }
        }

        return airdrops;
    }
}
