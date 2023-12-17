package com.project.meetmiddle.app.infrastructure.oauth.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.meetmiddle.app.infrastructure.oauth.dto.GoogleTokenResponse;
import com.project.meetmiddle.app.member.domain.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class GoogleOAuthClient implements OAuthClient{

    private static final String JWT_DELIMITER = "\\.";

    private final String googleRedirectUri;
    private final String googleClientId;
    private final String googleClientSecret;
    private final String googleTokenUri;
    private final ObjectMapper objectMapper;

    public GoogleOAuthClient(@Value("${spring.security.oauth2.google.redirect-uri}") String googleRedirectUri,
                             @Value("${spring.security.oauth2.google.client-id}")String googleClientId,
                             @Value("${spring.security.oauth2.google.client-secret}")String googleClientSecret,
                             @Value("${spring.security.oauth2.google.token-uri}")String googleTokenUri,
                             ObjectMapper objectMapper) {
        this.googleRedirectUri = googleRedirectUri;
        this.googleClientId = googleClientId;
        this.googleClientSecret = googleClientSecret;
        this.googleTokenUri = googleTokenUri;
        this.objectMapper = objectMapper;
    }

    @Override
    public Member getOAuthMember(String code) {
        RestTemplate restTemplate = new RestTemplate();

        GoogleTokenResponse googleTokenResponse =
                restTemplate.postForEntity(googleTokenUri, generateRequestParams(code), GoogleTokenResponse.class).getBody();

        String payload = getPayloadFrom(googleTokenResponse.getIdToken());
        String decodedPayload = decodeJwtPayload(payload);
        try{
            return generateMemberBy(decodedPayload);
        } catch (JsonProcessingException e){
            throw new IllegalArgumentException();
        }
    }

    private String getPayloadFrom(final String jwt){
        return jwt.split(JWT_DELIMITER)[1];
    }

    private String decodeJwtPayload(final String payload){
        return new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
    }

    private Member generateMemberBy(final String decodeIdToken) throws JsonProcessingException{
        Map<String, String> userInfo = objectMapper.readValue(decodeIdToken, HashMap.class);
        String email = userInfo.get("email");
        String name = userInfo.get("name");
        String profileImageUrl = userInfo.get("picture");

        return new Member(name, name, email, profileImageUrl, "1999-03-18");
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
