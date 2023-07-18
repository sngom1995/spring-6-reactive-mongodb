package sn.guru.springframework.reactivemongo.web.fn;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import sn.guru.springframework.reactivemongo.model.BeerDTO;
import sn.guru.springframework.reactivemongo.service.BeerService;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class BeerHandler {
    private final BeerService beerService;

    public Mono<ServerResponse> listBeers(ServerRequest request) {
        return ServerResponse.ok().body(beerService.listBeers(), BeerDTO.class);
    }

    public Mono<ServerResponse> getBeerById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().body(beerService.getBeerById(id), BeerDTO.class);
    }

    public Mono<ServerResponse> saveBeer(ServerRequest request) {
        Mono<BeerDTO> beerDTO = request.bodyToMono(BeerDTO.class);
        return beerDTO.flatMap(beerService::saveBeer)
               .flatMap(beer -> ServerResponse.created(UriComponentsBuilder.fromPath(BeerRouterConfig.BEER_BY_ID_PATH).build(beer.getId())).body(Mono.just(beer), BeerDTO.class));
    }

    public Mono<ServerResponse> getBeerByBeerName(ServerRequest request) {
        String beerName = request.pathVariable("beerName");
        return ServerResponse.ok().body(beerService.getBeerByBeerName(beerName), BeerDTO.class);
    }

    public Mono<ServerResponse> getBeerByBeerStyle(ServerRequest request) {
        String beerStyle = request.pathVariable("beerStyle");
        return ServerResponse.ok().body(beerService.getBeerByBeerStyle(beerStyle), BeerDTO.class);
    }

    public Mono<ServerResponse> updateBeer(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<BeerDTO> beerDTO = request.bodyToMono(BeerDTO.class);
        return beerDTO.flatMap(beer -> beerService.updateBeer(id, beer))
                .flatMap(beer -> ServerResponse.noContent().build());
    }
}
