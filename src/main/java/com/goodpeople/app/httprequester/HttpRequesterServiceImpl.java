package com.goodpeople.app.httprequester;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HttpRequesterServiceImpl implements HttpRequesterService {

    private final RestTemplate restTemplate;

    @Override
    public <T> T get(String httpUrl, MultiValueMap<String, String> queryParams, Class<T> clazz) {
        String uri = UriComponentsBuilder.fromHttpUrl(httpUrl)
                .queryParams(queryParams)
                .build()
                .toUriString();

        return restTemplate.getForEntity(uri, clazz).getBody();
    }

    @Override
    public <T> T get(String httpUrl, MultiValueMap<String, String> queryParams, ParameterizedTypeReference<T> parameterizedTypeReference) {
        String uri = UriComponentsBuilder.fromHttpUrl(httpUrl)
                                         .queryParams(queryParams)
                                         .build()
                                         .toUriString();

        return restTemplate.exchange(uri, HttpMethod.GET, null, parameterizedTypeReference, Collections.emptyMap()).getBody();
    }
}
