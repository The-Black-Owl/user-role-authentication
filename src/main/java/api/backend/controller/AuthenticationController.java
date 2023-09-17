package api.backend.controller;

import api.backend.dto.AuthenticationRequest;
import api.backend.dto.SignUpRequest;
import api.backend.dto.UserDTO;
import api.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private UserService userService;

    //method to login a user
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody AuthenticationRequest request){
        UserDTO user= userService.login(request);
        return  ResponseEntity.ok(user);
    }
    //method to register a user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody SignUpRequest request){
        UserDTO user= userService.register(request);
        return  ResponseEntity.created(URI.create("/user/"+user.getEmail())).body(user);}
}
