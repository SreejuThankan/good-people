package com.goodpeople.app.httprequester;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.MultiValueMap;

public interface HttpRequesterService {

    <T> T get(String httpUrl, MultiValueMap<String, String> queryParams, Class<T> clazz);

    <T> T get(String httpUrl, MultiValueMap<String, String> queryParams, ParameterizedTypeReference<T> parameterizedTypeReference);
}
