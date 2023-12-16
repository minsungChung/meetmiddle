package com.project.meetmiddle.app.infrastructure.oauth.client;

@FunctionalInterface
public interface OAuthClient {
    String getOAuthMember(final String code);
}
