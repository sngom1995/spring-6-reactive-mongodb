package sn.guru.springframework.reactivemongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import sn.guru.springframework.reactivemongo.domain.Beer;

public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {
}
