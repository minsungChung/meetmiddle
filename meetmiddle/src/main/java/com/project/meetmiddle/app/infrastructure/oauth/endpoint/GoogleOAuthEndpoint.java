package com.project.meetmiddle.app.infrastructure.oauth.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoogleOAuthEndpoint implements OAuthEndpoint{

    private static final String GOOGLE_OAUTH_END_POINT =
            "https://accounts.google.com/o/oauth2/v2/auth";
    private static final List<String> SCOPES =
            List.of("https://www.googleapis.com/auth/userinfo.profile",
                    "https://www.googleapis.com/auth/userinfo.email");

    private final String googleRedirectUri;
    private final String googleClientId;

    public GoogleOAuthEndpoint(@Value("${spring.security.oauth2.google.redirect-uri}") String googleRedirectUri,
                               @Value("${spring.security.oauth2.google.client-id}") String googleClientId) {
        this.googleRedirectUri = googleRedirectUri;
        this.googleClientId = googleClientId;
    }

    @Override
    public String generate() {
        return GOOGLE_OAUTH_END_POINT + "?"
                + "client_id=" + googleClientId + "&"
                + "redirect_uri=" + googleRedirectUri + "&"
                + "response_type=code&"
                + "scope=" + String.join(" ", SCOPES);
    }
}
