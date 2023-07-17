package sn.guru.springframework.reactivemongo.service;

import reactor.core.publisher.Mono;
import sn.guru.springframework.reactivemongo.model.BeerDTO;

public interface BeerService {

    Mono<BeerDTO> saveBeer(BeerDTO beerDTO);
    Mono<BeerDTO> getBeerById(String id);
}
