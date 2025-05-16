package tr.com.muskar.walletAnalyzer.service;

import tr.com.muskar.walletAnalyzer.model.dto.airdrop.AirdropDto;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExportService {
    ByteArrayInputStream exportAirdropsToCSV(List<AirdropDto> airdrops);
}
