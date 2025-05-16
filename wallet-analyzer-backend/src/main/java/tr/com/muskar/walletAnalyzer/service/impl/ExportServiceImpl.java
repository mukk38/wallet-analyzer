package tr.com.muskar.walletAnalyzer.service.impl;



import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import tr.com.muskar.walletAnalyzer.model.dto.airdrop.AirdropDto;
import tr.com.muskar.walletAnalyzer.service.ExportService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    @Override
    public ByteArrayInputStream exportAirdropsToCSV(List<AirdropDto> airdrops) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(
                "Token Symbol", "Token Name", "Contract Address", "From", "Received At", "Value"
        );

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(out);
             CSVPrinter csvPrinter = new CSVPrinter(writer, format)) {

            for (AirdropDto dto : airdrops) {
                csvPrinter.printRecord(
                        dto.getTokenSymbol(),
                        dto.getTokenName(),
                        dto.getContractAddress(),
                        dto.getFromAddress(),
                        dto.getReceivedAt(),
                        dto.getValue()
                );
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("CSV oluşturulamadı", e);
        }
    }
}

