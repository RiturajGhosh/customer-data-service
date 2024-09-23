package com.amex.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

@Component
public class APIClient {
    private final WebClient webClient;

    @Autowired
    public APIClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public <R, S> Flux<R> callApiFluxResponse(String uri, S requestBody, HttpMethod httpMethod, Class<R> responseType, Map<String, String> headers) {
        return buildWebClientRequest(uri, httpMethod, headers).body(Mono.just(requestBody), requestBody.getClass())
                .exchangeToFlux(captureFluxResponse(responseType));
    }

    private WebClient.RequestBodySpec buildWebClientRequest(String uri, HttpMethod httpMethod, Map<String, String> headers) {
        var builtWebClient = webClient.method(httpMethod)
                .uri(uri);
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(builtWebClient::header);
        }
        return builtWebClient;
    }

    private <S> Function<ClientResponse, Flux<S>> captureFluxResponse(Class<S> responseType) {
        return clientResponse -> {
            if (clientResponse.statusCode().is4xxClientError()) {
                return clientResponse.createException()
                        .flatMapMany(ex -> Flux.error(new HttpClientErrorException(clientResponse.statusCode(),
                                ex.getResponseBodyAsString())));
            } else if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.createException()
                        .flatMapMany(ex -> Flux.error(new HttpServerErrorException(clientResponse.statusCode(),
                                ex.getResponseBodyAsString())));
            } else {
                return clientResponse.bodyToFlux(responseType).log();
            }
        };
    }
}
