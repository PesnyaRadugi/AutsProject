package ua.auts.project.AutsProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.auts.project.AutsProject.config.auth.TokenProvider;
import ua.auts.project.AutsProject.dtos.JwtDto;
import ua.auts.project.AutsProject.dtos.SignInDto;
import ua.auts.project.AutsProject.dtos.SignUpDto;
import ua.auts.project.AutsProject.entities.User;
import ua.auts.project.AutsProject.services.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService service;
    @Autowired
    private TokenProvider tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> sigUp(@RequestBody @Validated SignUpDto data) {
        try {
            service.signUp(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Validated SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accesToken = tokenService.generateAccesToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JwtDto(accesToken));
    }
}
