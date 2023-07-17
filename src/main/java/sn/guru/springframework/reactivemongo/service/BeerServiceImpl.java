package sn.guru.springframework.reactivemongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Mono<BeerDTO> getBeerById(String id) {
        return beerRepository.findById(id)
                .map(beerMapper::beerToBeerDto);
    }
}
