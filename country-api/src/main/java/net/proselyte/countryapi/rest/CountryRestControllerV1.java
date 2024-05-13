package net.proselyte.countryapi.rest;

import lombok.RequiredArgsConstructor;
import net.proselyte.countryapi.dto.CountryDto;
import net.proselyte.countryapi.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryRestControllerV1 {

    private final CountryService countryService;

    @PostMapping("/process")
    public ResponseEntity<?> processCountries() {
        countryService.processCountries();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> findAllCountries() {
        List<CountryDto> countryDtos = countryService.getAllCountries();
        return ResponseEntity.ok(countryDtos);
    }

    @GetMapping("/cc2/{alpha2}")
    public ResponseEntity<?> findCountryByAlpha2Code(@PathVariable String alpha2) {
        CountryDto countryDto = countryService.getDetailsByAlpha2Code(alpha2);
        return ResponseEntity.ok(countryDto);
    }

}
