package sn.guru.springframework.reactivemongo.web.fn;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class BeerRouterConfig {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_BY_ID_PATH = BEER_PATH+"/{id}";

    private final BeerHandler beerHandler;
    @Bean
    public RouterFunction<ServerResponse> beerRoutes() {
        return route().GET(BEER_PATH, accept(APPLICATION_JSON), beerHandler::listBeers)
                .GET(BEER_BY_ID_PATH, accept(APPLICATION_JSON), beerHandler::getBeerById)
                .build();
    }


}
