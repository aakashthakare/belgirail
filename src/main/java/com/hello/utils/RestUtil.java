package com.hello.utils;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

public final class RestUtil {

    public static <T> T get(String uri, Class<T> response) {
        WebClient.RequestHeadersSpec<?> spec = WebClient.builder().clientConnector(new ReactorClientHttpConnector(
                                                                                            HttpClient.create().followRedirect(true)
                                                                                   )).build().get().uri(uri);
        return spec.retrieve().toEntity(response).block().getBody();
    }
}
