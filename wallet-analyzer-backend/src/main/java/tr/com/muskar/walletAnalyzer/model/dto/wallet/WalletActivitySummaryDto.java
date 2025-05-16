package tr.com.muskar.walletAnalyzer.model.dto.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WalletActivitySummaryDto {

    private LocalDateTime firstTxTime;
    private LocalDateTime lastTxTime;
    private int totalTxCount;
    private double totalFeeEth;
    private double avgFeeEth;
}
