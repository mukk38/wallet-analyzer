package tr.com.muskar.walletAnalyzer.model.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class EtherscanTxResponse {
    private String status;
    private String message;
    private List<EtherscanTxResult> result;

//    public List<EtherscanTxResult> getParsedResult() {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            return mapper.readValue(result, new TypeReference<List<EtherscanTxResult>>() {});
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to parse result field", e);
//        }
//    }
}
