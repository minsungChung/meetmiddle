package com.project.meetmiddle.app.auth.controller;


import com.project.meetmiddle.app.auth.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/{oauthProvider}/link")
    public void generateLink(@PathVariable("oauthProvider") final String oauthProvider){
        authService.request();
    }

    @GetMapping("/{oauthProvider}/token")
    public ResponseEntity<String> generateToken(@PathVariable("oauthProvider") final String oauthProvider, @RequestParam(name = "code") String code){
        System.out.println(authService.generateTokenWithCode(code));
        return ResponseEntity.ok("토큰 받아오기 성공");
    }
}
