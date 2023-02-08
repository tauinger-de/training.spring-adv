package com.example.pizza.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhoAmIController {

    static final String ROOT = "/me";
    public static final String ME_ENDPOINT = ROOT;

    @GetMapping(ME_ENDPOINT)
    public Object getMe(@AuthenticationPrincipal Object principal) {
        return principal;
    }
}
