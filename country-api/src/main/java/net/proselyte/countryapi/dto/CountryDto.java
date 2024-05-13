package net.proselyte.countryapi.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.proselyte.countryapi.entity.CountryDocument;
import net.proselyte.countryapi.entity.CountryEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDto {
    @JsonProperty("cca2")
    private String alpha2;
    @JsonProperty("cca3")
    private String alpha3;
    private String region;
    private List<String> capital;
    private String subregion;
    private Integer area;
    private Integer population;
    private Boolean independent;

    public static CountryDto fromJpaEntity(CountryEntity entity) {
        if(Objects.isNull(entity)) {
            return null;
        }
        return CountryDto.builder()
                .alpha2(entity.getAlpha2())
                .alpha3(entity.getAlpha3())
                .capital(StringUtils.hasText(entity.getCapital()) ? List.of(entity.getCapital()) : null)
                .build();
    }

    public CountryEntity toJpaEntity() {
        return CountryEntity.builder()
                .alpha2(this.getAlpha2())
                .alpha3(this.getAlpha3())
                .capital(CollectionUtils.isEmpty(this.getCapital()) ? null : this.getCapital().getFirst())
                .build();
    }

    public static CountryDto fromMongoDocument(CountryDocument entity) {
        if(Objects.isNull(entity)) {
            return null;
        }
        return CountryDto.builder()
                .alpha2(entity.getAlpha2())
                .alpha3(entity.getAlpha3())
                .region(entity.getRegion())
                .subregion(entity.getSubregion())
                .capital(List.of(entity.getCapital()))
                .area(entity.getArea())
                .population(entity.getPopulation())
                .independent(entity.getIndependent())
                .build();
    }

    public CountryDocument toMongoDocument() {
        return CountryDocument.builder()
                .alpha2(this.getAlpha2())
                .alpha3(this.getAlpha3())
                .region(this.getRegion())
                .subregion(this.getSubregion())
                .capital(CollectionUtils.isEmpty(this.getCapital()) ? null : this.getCapital().get(0))
                .area(this.getArea())
                .population(this.getPopulation())
                .independent(this.getIndependent())
                .build();
    }
}
