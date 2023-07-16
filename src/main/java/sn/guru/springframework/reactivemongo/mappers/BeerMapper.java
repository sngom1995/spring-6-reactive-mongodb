package sn.guru.springframework.reactivemongo.mappers;

import org.mapstruct.Mapper;
import sn.guru.springframework.reactivemongo.domain.Beer;
import sn.guru.springframework.reactivemongo.model.BeerDTO;

@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO beer);
    BeerDTO beerToBeerDto(Beer beer);
}
