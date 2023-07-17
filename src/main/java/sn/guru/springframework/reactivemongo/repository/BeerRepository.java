package sn.guru.springframework.reactivemongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import sn.guru.springframework.reactivemongo.domain.Beer;

public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {
    Mono<Beer> findFirstByBeerName(String beerName);
    Mono<Beer> findFirstByBeerStyle(String beerStyle);
}
