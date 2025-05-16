package tr.com.muskar.walletAnalyzer.service;

import tr.com.muskar.walletAnalyzer.model.dto.EtherscanTxResult;
import tr.com.muskar.walletAnalyzer.model.dto.airdrop.AirdropDto;
import tr.com.muskar.walletAnalyzer.model.dto.token.EtherscanTokenTxResult;
import tr.com.muskar.walletAnalyzer.model.dto.token.stats.TokenStatsDto;
import tr.com.muskar.walletAnalyzer.model.dto.wallet.WalletActivitySummaryDto;

import java.util.List;

public interface WalletService {
    List<EtherscanTxResult> getTransactions(String address);
    List<EtherscanTokenTxResult> getTokenTransfers(String address);
    List<TokenStatsDto> getTokenStats(String address);
    WalletActivitySummaryDto getWalletActivitySummary(String address);
    List<AirdropDto> detectAirdrops(String address);
}
