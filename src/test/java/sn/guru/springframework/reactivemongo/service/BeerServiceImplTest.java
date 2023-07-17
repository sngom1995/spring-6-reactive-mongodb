package sn.guru.springframework.reactivemongo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import sn.guru.springframework.reactivemongo.domain.Beer;
import sn.guru.springframework.reactivemongo.mappers.BeerMapper;
import sn.guru.springframework.reactivemongo.model.BeerDTO;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;


@SpringBootTest
class BeerServiceImplTest {

    @Autowired
    BeerService beerService;
    @Autowired
    BeerMapper beerMapper;

    BeerDTO beerDTO;

    @BeforeEach
    void setUp() {
        beerDTO = beerMapper.beerToBeerDto(getBeer());
    }

    @Test
    void saveBeerTest() {
        Mono<BeerDTO> beerDTOMono = beerService.saveBeer(beerDTO);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        beerDTOMono.subscribe(beerDTO1 -> {
            System.out.println(beerDTO1.getId());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void getBeerByIdTest() {
        Mono<BeerDTO> beerDTOMono = beerService.saveBeer(beerDTO);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        beerDTOMono.subscribe(beerDTO1 -> {
            Mono<BeerDTO> beerDTOById = beerService.getBeerById(beerDTO1.getId());
            beerDTOById.subscribe(beerDTO2 -> {
                System.out.println(beerDTO2.getId());
                atomicBoolean.set(true);
            });
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void getBeerByBeerNameTest() {
        Mono<BeerDTO> beerDTOMono = beerService.saveBeer(beerDTO);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        beerDTOMono.subscribe(beerDTO1 -> {
            Mono<BeerDTO> beerDTOById = beerService.getBeerByBeerName(beerDTO1.getBeerName());
            beerDTOById.subscribe(beerDTO2 -> {
                System.out.println(beerDTO2.getId());
                atomicBoolean.set(true);
            });
        });

        await().untilTrue(atomicBoolean);
    }

    Beer getBeer() {
        return Beer.builder()
                .beerName("Guru")
                .beerStyle("IPA")
                .quantityOnHand(200)
                .price(BigDecimal.valueOf(1000.0))
                .build();
    }
}
