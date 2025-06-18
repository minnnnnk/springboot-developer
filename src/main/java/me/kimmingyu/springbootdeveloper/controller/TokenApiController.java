package me.kimmingyu.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.kimmingyu.springbootdeveloper.dto.CreateAccessTokenRequest;
import me.kimmingyu.springbootdeveloper.dto.CreateAccesstokenResponse;
import me.kimmingyu.springbootdeveloper.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccesstokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request){
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccesstokenResponse(newAccessToken));
    }
}

