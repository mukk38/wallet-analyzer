package tr.com.muskar.walletAnalyzer.model.dto.airdrop;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AirdropDto {
    private String tokenSymbol;
    private String tokenName;
    private String contractAddress;
    private String fromAddress;
    private LocalDateTime receivedAt;
    private String value;
}
