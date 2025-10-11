package com.digenty.app.clients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.UnknownContentTypeException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientUtils {
    private final RestClient restClient;

    private static Consumer<HttpHeaders> createHeaders(HttpHeaders httpHeaders) {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return h -> h.addAll(httpHeaders);
    }

    public <T, R> ResponseEntity<R> post(String url, HttpHeaders httpHeaders, T body, Class<R> rClass) {
        try {
            return restClient.post()
                    .uri(url)
                    .body(body)
                    .headers(createHeaders(httpHeaders))
                    .retrieve()
                    .toEntity(rClass);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            if (e.getMessage().contains("You have insufficient balance")) {
                throw new RuntimeException(e.getMessage());
            } else {
                throw new RuntimeException(e);
            }
        }
        catch (UnknownContentTypeException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public <T, R> ResponseEntity<R> put(String url, HttpHeaders httpHeaders, T body, Class<R> rClass) {
        return restClient.put()
                .uri(url)
                .body(body)
                .headers(createHeaders(httpHeaders))
                .retrieve().toEntity(rClass);
    }

    public <R> ResponseEntity<R> get(String url, HttpHeaders httpHeaders, Class<R> rClass) {

        ResponseEntity<R> entity = restClient.get()
                .uri(url)
                .headers(createHeaders(httpHeaders))
                .retrieve().toEntity(rClass);
        return entity;
    }

    private String getTextFromInputStream(InputStream inputStream) throws IOException {
        Scanner scanner = new Scanner(inputStream);
        StringBuilder bodyBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            bodyBuilder.append(scanner.nextLine()).append("\n");
        }

        scanner.close();
        inputStream.close();
        return bodyBuilder.toString();
    }




}
