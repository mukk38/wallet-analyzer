package tr.com.muskar.walletAnalyzer.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EtherscanTxResult {

    private String blockNumber;
    private String timeStamp;
    private String hash;
    private String from;
    private String to;
    private String value;
    private String gas;
    private String gasPrice;
}
