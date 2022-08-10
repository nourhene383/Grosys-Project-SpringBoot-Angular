package com.example.grosysbackend.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authentif.Entity.Role;
import com.example.authentif.Entity.User;
import com.example.authentif.Service.UserService;
import com.example.grosysbackend.Entity.Role;
import com.example.grosysbackend.Entity.User;
import com.example.grosysbackend.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser (@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/token/refresh")
    public  void refreshToken (HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("*****\n");
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("*****\n");
            try {
                System.out.println("*****\n");
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                System.out.println("*****\n");
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                System.out.println("*****\n");
                JWTVerifier verfier = JWT.require(algorithm).build();
                System.out.println("*****\n");
                DecodedJWT decodedJWT = verfier.verify(refresh_token);
                System.out.println("*****\n");
                String username = decodedJWT.getSubject();
                System.out.println("****"+username);
                User user = userService.getUser(username);
                System.out.println(user);
                String access_token = JWT.create()
                        .withSubject(user.getFullName())
                        .withExpiresAt(new Date(System.currentTimeMillis() +70 *60 *1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                //  response.setHeader("access_token",access_token);
                // response.setHeader("refresh_token",refresh_token);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //  response.sendError(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);}
        }
        else{
            throw new  RuntimeException("Refresh Token is missing");
        }
    }

}
@Data
class RoleToUserForm{
    private String username;
    private String rolename;

}