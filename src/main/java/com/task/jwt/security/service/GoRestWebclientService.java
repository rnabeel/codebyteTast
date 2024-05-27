package com.task.jwt.security.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.jwt.security.exception.ApplicationException;
import com.task.jwt.security.exception.ApplicationExceptionMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Service
public class GoRestWebclientService {


    private final WebClient webClientGoRest;

    public GoRestWebclientService(WebClient webClientCrm) {
        this.webClientGoRest = webClientCrm;
    }


    public Object getData(String url) {
        return handleResponse(webClientGoRest.get().uri(url).accept(MediaType.APPLICATION_JSON)
                        .retrieve().toEntity(Object.class),
                null);
    }

    private <T> T handleResponse(Mono<ResponseEntity<T>> responseMono, String porgaCode) {
        try {
            ResponseEntity<T> response = responseMono.block();
            return response.getBody();
        } catch (WebClientResponseException e) {
            ApplicationExceptionMapper.APIError errorDetails = parseErrorDetails(e);
            throw new ApplicationException(porgaCode,errorDetails.getErrorCode(),errorDetails.getArguments());
        }
    }

    private ApplicationExceptionMapper.APIError parseErrorDetails(WebClientResponseException e) {
        String errorCode = null;
        List<String> arguments = null;
        if (e.getResponseBodyAsString() != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode errorNode = objectMapper.readTree(e.getResponseBodyAsString());
                errorCode = errorNode.get("errorCode").asText();
                arguments = Arrays.asList(objectMapper.convertValue(errorNode.get("arguments"), String[].class));
            } catch (IOException ex) {
            }
        }
        return new ApplicationExceptionMapper.APIError(errorCode, arguments.toArray());
    }
}
