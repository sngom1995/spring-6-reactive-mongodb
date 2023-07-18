package sn.guru.springframework.reactivemongo.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sn.guru.springframework.reactivemongo.model.BeerDTO;

public interface BeerService {

    Mono<BeerDTO> saveBeer(BeerDTO beerDTO);
    Flux<BeerDTO> listBeers();
    Mono<BeerDTO> getBeerById(String id);
    Mono<BeerDTO> getBeerByBeerName(String beerName);
    Mono<BeerDTO> getBeerByBeerStyle(String beerStyle);
}
