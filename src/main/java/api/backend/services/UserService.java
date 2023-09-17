package api.backend.services;

import api.backend.dto.AuthenticationRequest;
import api.backend.dto.SignUpRequest;
import api.backend.dto.UserDTO;
import api.backend.entities.Role;
import api.backend.entities.User;
import api.backend.mapper.UserMapper;
import api.backend.repository.RoleRepository;
import api.backend.repository.UserReposritory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserReposritory userReposritory;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserMapper userMapper;

    public UserDTO login(AuthenticationRequest request) {
        //check if the user exist, we do this by email
        User user = userReposritory.findByEmail(request.email())
                .orElseThrow(()->new UsernameNotFoundException(HttpStatus.NOT_FOUND.toString()));
        //is the user password valid
        if(encoder.matches(CharBuffer.wrap(request.password()),user.getPassword())){
            return userMapper.toUserDTO(user);
        }
        throw new UsernameNotFoundException(HttpStatus.NOT_FOUND.toString());
    }

    //registration method
    public UserDTO register(SignUpRequest request){
        Optional<User> authUser=userReposritory.findByEmail(request.email());
        if (authUser.isPresent()){
            throw new UsernameNotFoundException("User already exist");
        }
        //create user role
        HashSet<Role> userRole=new HashSet<>();
        userRole.add(new Role("USER"));

        User user=userMapper.signUpToUser(request);
        user.setRole(userRole);
        user.setPassword(encoder.encode(CharBuffer.wrap(request.password())));

        User savedUSer=userReposritory.save(user);
        //
        return userMapper.toUserDTO(savedUSer);
    }
}
