package net.proselyte.countryapi.repository.mongo;

import net.proselyte.countryapi.entity.CountryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("cassandraCountryRepository")
public interface CountryMongoRepository extends MongoRepository<CountryDocument, String> {
    CountryDocument findByAlpha2(String alpha2);
}
