package net.proselyte.countryapi.repository;

import net.proselyte.countryapi.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CountryJpaRepository extends JpaRepository<CountryEntity, String> {
    CountryEntity findByAlpha2(String alpha2);
}
