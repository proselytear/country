package net.proselyte.countryapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDocument {
    @Id
    String alpha2;
    String alpha3;
    String capital;
    String region;
    String subregion;
    Integer area;
    Integer population;
    Boolean independent;
}
