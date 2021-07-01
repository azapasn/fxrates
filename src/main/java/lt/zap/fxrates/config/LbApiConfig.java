package lt.zap.fxrates.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Configuration
public class LbApiConfig {

    @Value("${fx.currencyList}")
    private String currencyListLink;

    @Value("${fx.currencyHistory}")
    private String currencyHistoryLink;

    public String getCurrencyHistoryLink(String currency, String dtFrom, String dtTo){
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(this.currencyHistoryLink)
                .queryParam("tp", "")
                .queryParam("ccy", currency)
                .queryParam("dtFrom", dtFrom)
                .queryParam("dtTo", dtTo);
        return builder.toUriString();
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        Jaxb2RootElementHttpMessageConverter converter = new Jaxb2RootElementHttpMessageConverter();

        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }
}
