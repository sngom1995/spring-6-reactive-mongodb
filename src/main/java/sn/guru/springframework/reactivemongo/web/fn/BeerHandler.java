package sn.guru.springframework.reactivemongo.web.fn;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import sn.guru.springframework.reactivemongo.model.BeerDTO;
import sn.guru.springframework.reactivemongo.service.BeerService;

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
}
