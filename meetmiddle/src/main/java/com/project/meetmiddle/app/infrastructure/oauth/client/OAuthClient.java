package com.project.meetmiddle.app.infrastructure.oauth.client;

import com.project.meetmiddle.app.member.domain.Member;

@FunctionalInterface
public interface OAuthClient {
    Member getOAuthMember(final String code);
}
