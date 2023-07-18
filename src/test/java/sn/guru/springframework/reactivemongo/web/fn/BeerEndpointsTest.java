package sn.guru.springframework.reactivemongo.web.fn;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import sn.guru.springframework.reactivemongo.model.BeerDTO;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class BeerEndpointsTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testListBeers() {
        webTestClient.get().uri(BeerRouterConfig.BEER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody()
                .jsonPath("$.size()",hasSize(greaterThan(1)));
    }

    @Test
    void testSaveBeers() {
        webTestClient.post().uri(BeerRouterConfig.BEER_PATH)
                .bodyValue(BeerDTO.builder().beerName("Beer 1").beerStyle("Style 1").upc("126153").price(BigDecimal.valueOf(12.5)).build())
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.beerName").isEqualTo("Beer 1");
    }

    @Test
    void testUpdate(){
        webTestClient.put().uri(BeerRouterConfig.BEER_BY_ID_PATH, "64b46d99a8f45a6acf879e47")
                .bodyValue(BeerDTO.builder().beerName("Beer 1").beerStyle("Style 1").upc("126153").price(BigDecimal.valueOf(12.5)).build())
                .exchange()
                .expectStatus().isNoContent();
    }
}
