package sn.guru.springframework.reactivemongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sn.guru.springframework.reactivemongo.domain.Beer;
import sn.guru.springframework.reactivemongo.mappers.BeerMapper;
import sn.guru.springframework.reactivemongo.model.BeerDTO;
import sn.guru.springframework.reactivemongo.repository.BeerRepository;


@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    @Override
    public Mono<BeerDTO> saveBeer(BeerDTO beerDTO) {
        Beer beer = beerMapper.beerDtoToBeer(beerDTO);
        return beerRepository.save(beer)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> getBeerById(String id) {
        return beerRepository.findById(id)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> getBeerByBeerName(String beerName) {
        return beerRepository.findFirstByBeerName(beerName)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> getBeerByBeerStyle(String beerStyle) {
        return beerRepository.findFirstByBeerStyle(beerStyle)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> updateBeer(String id, BeerDTO beerDTO) {
        Mono<Beer> beer = beerRepository.findById(id);
        return beer.map(b -> {
            b.setBeerName(beerDTO.getBeerName());
            b.setBeerStyle(beerDTO.getBeerStyle());
            b.setPrice(beerDTO.getPrice());
            return b;
        }).flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDto);
    }
}
