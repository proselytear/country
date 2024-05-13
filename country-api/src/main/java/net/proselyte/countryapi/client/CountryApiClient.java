package net.proselyte.countryapi.client;

import net.proselyte.countryapi.dto.CountryDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CountryApiClient {
    private final RestTemplate restTemplate;

    public CountryApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<CountryDto> getAllCountries() {
        String url = "https://restcountries.com/v3.1/all?fields=cca2,cca3,capital,region,subregion,area,population,independent";
        CountryDto[] array = restTemplate.getForObject(url, CountryDto[].class);
        return Arrays.asList(array);
    }
}
