package tr.com.muskar.walletAnalyzer.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EtherscanConfig {

    @Value("${etherscan.api.url}")
    private String etherscanApiUrl;

    @Bean
    public WebClient etherscanWebClient() {
        return WebClient.builder()
                .baseUrl(etherscanApiUrl)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(50 * 1024 * 1024))
                .build();
    }
}
