package tr.com.muskar.walletAnalyzer.model.dto.token.stats;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenStatsDto {

    private String tokenSymbol;
    private String tokenName;
    private String contractAddress;
    private long transferCount;
}
