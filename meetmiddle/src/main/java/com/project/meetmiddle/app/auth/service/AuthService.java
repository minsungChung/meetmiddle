package com.project.meetmiddle.app.auth.service;

import com.project.meetmiddle.app.infrastructure.oauth.client.OAuthClient;
import com.project.meetmiddle.app.infrastructure.oauth.endpoint.OAuthEndpoint;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final OAuthEndpoint oAuthEndpoint;
    private final OAuthClient oAuthClient;
    private final HttpServletResponse response;

    public void request() {
        try {
            response.sendRedirect(oAuthEndpoint.generate());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateTokenWithCode(final String code){
        return oAuthClient.getOAuthMember(code);
    }
}
