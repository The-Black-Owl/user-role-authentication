package api.backend.controller;

import api.backend.dto.AuthenticationRequest;
import api.backend.dto.UserDTO;
import api.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
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

}
