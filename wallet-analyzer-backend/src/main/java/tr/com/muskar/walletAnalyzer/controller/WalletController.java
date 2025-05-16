package tr.com.muskar.walletAnalyzer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.muskar.walletAnalyzer.model.dto.EtherscanTxResult;
import tr.com.muskar.walletAnalyzer.model.dto.airdrop.AirdropDto;
import tr.com.muskar.walletAnalyzer.model.dto.token.EtherscanTokenTxResult;
import tr.com.muskar.walletAnalyzer.model.dto.token.stats.TokenStatsDto;
import tr.com.muskar.walletAnalyzer.model.dto.wallet.WalletActivitySummaryDto;
import tr.com.muskar.walletAnalyzer.service.ExportService;
import tr.com.muskar.walletAnalyzer.service.WalletService;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class WalletController {


    private final WalletService walletService;
    private final ExportService exportService;

    @GetMapping("/{address}/transactions")
    public List<EtherscanTxResult> getTransactions(@PathVariable String address) {
        return walletService.getTransactions(address);
    }

    @GetMapping("/{address}/token-transfers")
    public List<EtherscanTokenTxResult> getTokenTransfers(@PathVariable String address) {
        return walletService.getTokenTransfers(address);
    }

    @GetMapping("/{address}/token-stats")
    public List<TokenStatsDto> getTokenStats(@PathVariable String address) {
        return walletService.getTokenStats(address);
    }

    @GetMapping("/{address}/summary")
    public WalletActivitySummaryDto getWalletSummary(@PathVariable String address) {
        return walletService.getWalletActivitySummary(address);
    }

    @GetMapping("/{address}/airdrops")
    public List<AirdropDto> getAirdrops(@PathVariable String address) {
        return walletService.detectAirdrops(address);
    }

    @GetMapping("/{address}/airdrops/csv")
    public ResponseEntity<InputStreamResource> exportAirdropsCsv(@PathVariable String address) {
        List<AirdropDto> airdrops = walletService.detectAirdrops(address);
        ByteArrayInputStream csvData = exportService.exportAirdropsToCSV(airdrops);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=airdrops.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(csvData));
    }
}
