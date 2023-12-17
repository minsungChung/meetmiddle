package com.project.meetmiddle.app.auth.service;

import com.project.meetmiddle.app.auth.dto.TokenResponse;
import com.project.meetmiddle.app.auth.support.JwtTokenProvider;
import com.project.meetmiddle.app.infrastructure.oauth.client.OAuthClient;
import com.project.meetmiddle.app.infrastructure.oauth.endpoint.OAuthEndpoint;
import com.project.meetmiddle.app.member.domain.Member;
import com.project.meetmiddle.app.member.domain.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final OAuthEndpoint oAuthEndpoint;
    private final OAuthClient oAuthClient;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpServletResponse response;

    private Member saveMember(final Member member){
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    public void request() {
        try {
            response.sendRedirect(oAuthEndpoint.generate());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TokenResponse generateTokenWithCode(final String code){
        Member oAuthMember = oAuthClient.getOAuthMember(code);
        String email = oAuthMember.getEmail();
        memberRepository.save(oAuthMember);
        Member foundMember = memberRepository.findByEmail(email);
        System.out.println(foundMember.getId());
        String accessToken = jwtTokenProvider.createToken(String.valueOf(foundMember.getId()));
        return new TokenResponse(accessToken);
    }

}
