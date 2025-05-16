package tr.com.muskar.walletAnalyzer.model.dto.token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class EtherscanTokenTxResponse {
    private String status;
    private String message;
    private List<EtherscanTokenTxResult> result;

//    public List<EtherscanTokenTxResult> getParsedResult() {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            return mapper.readValue(result, new TypeReference<List<EtherscanTokenTxResult>>() {});
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to parse result field", e);
//        }
//    }
}
