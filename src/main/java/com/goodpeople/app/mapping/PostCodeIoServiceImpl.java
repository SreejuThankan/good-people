package com.goodpeople.app.mapping;

import com.goodpeople.app.httprequester.HttpRequesterService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostCodeIoServiceImpl implements PostCodeIoService {

    private static final String BASE_URL = "https://postcodes.io/postcodes/";

    private final HttpRequesterService httpRequesterService;

    @Override
    public PostCodeDetails getDetailsForPostCode(String postCode) {
        return httpRequesterService.get(BASE_URL + postCode, null, PostCodeIoEntity.class).getResult();
    }

    @Value
    private static class PostCodeIoEntity {
        PostCodeDetails result;
    }
}
