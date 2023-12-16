package com.project.meetmiddle.app.infrastructure.oauth.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleOAuthClient implements OAuthClient{

    private final String googleRedirectUri;
    private final String googleClientId;
    private final String googleClientSecret;
    private final String googleTokenUri;

    public GoogleOAuthClient(@Value("${spring.security.oauth2.google.redirect-uri}") String googleRedirectUri,
                             @Value("${spring.security.oauth2.google.client-id}")String googleClientId,
                             @Value("${spring.security.oauth2.google.client-secret}")String googleClientSecret,
                             @Value("${spring.security.oauth2.google.token-uri}")String googleTokenUri) {
        this.googleRedirectUri = googleRedirectUri;
        this.googleClientId = googleClientId;
        this.googleClientSecret = googleClientSecret;
        this.googleTokenUri = googleTokenUri;
    }

    @Override
    public String getOAuthMember(String code) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(googleTokenUri, generateRequestParams(code), String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }
        return "구글 로그인 요청 처리 실패";
    }

    private MultiValueMap<String, String> generateRequestParams(final String code){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", googleClientId);
        params.add("client_secret", googleClientSecret);
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", googleRedirectUri);
        return params;
    }

}
