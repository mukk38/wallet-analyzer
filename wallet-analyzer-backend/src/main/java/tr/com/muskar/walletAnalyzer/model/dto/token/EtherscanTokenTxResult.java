package tr.com.muskar.walletAnalyzer.model.dto.token;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EtherscanTokenTxResult {

    private String blockNumber;
    private String timeStamp;
    private String hash;
    private String from;
    private String to;
    private String value;
    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;
    private String contractAddress;
}
