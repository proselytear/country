package net.proselyte.countryapi.service;

import lombok.RequiredArgsConstructor;
import net.proselyte.countryapi.client.CountryApiClient;
import net.proselyte.countryapi.dto.CountryDto;
import net.proselyte.countryapi.entity.CountryDocument;
import net.proselyte.countryapi.entity.CountryEntity;
import net.proselyte.countryapi.repository.CountryJpaRepository;
import net.proselyte.countryapi.repository.mongo.CountryMongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryApiClient apiClient;
    private final CountryMongoRepository countryMongoRepository;
    private final CountryJpaRepository countryJpaRepository;


    public void processCountries() {
        List<CountryDto> countryDtos = apiClient.getAllCountries();

        List<CountryDocument> countryDocuments = countryDtos.stream()
                .filter(dto -> !dto.getAlpha2().contains("P"))
                .map(CountryDto::toMongoDocument)
                .toList();

        List<CountryEntity> countryEntities = countryDtos.stream()
                        .map(CountryDto::toJpaEntity)
                                .toList();

        countryJpaRepository.deleteAll();
        countryMongoRepository.deleteAll();

        countryJpaRepository.saveAll(countryEntities);
        countryMongoRepository.saveAll(countryDocuments);

    }

    public List<CountryDto> getAllCountries() {
        List<CountryEntity> countryEntities = countryJpaRepository.findAll();

        if(CollectionUtils.isEmpty(countryEntities)) {
            return Collections.emptyList();
        }

        return countryEntities.stream()
                .map(CountryDto::fromJpaEntity)
                .toList();
    }


    public CountryDto getDetailsByAlpha2Code(String alpha2Code) {
        CountryDocument document = countryMongoRepository.findByAlpha2(alpha2Code);
        if(Objects.nonNull(document)) {
            return CountryDto.fromMongoDocument(document);
        }

        CountryEntity countryEntity = countryJpaRepository.findByAlpha2(alpha2Code);

        return CountryDto.fromJpaEntity(countryEntity);
    }

}
