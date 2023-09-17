package api.backend.config;

import api.backend.dto.UserDTO;
import api.backend.entities.User;
import api.backend.mapper.UserMapper;
import api.backend.repository.UserReposritory;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import com.auth0.jwt.JWT;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
//    private static final String SECRET_KEY="455426e6e706bb0b21685c5f40963a2d3ae33556d8e4b13cb6535a9a2e348bc2\n";
    @Value(value = "${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Autowired
    private UserReposritory userReposritory;

    private final UserMapper userMapper;
    //this method prevents the secret key being stored as a raw values
    @PostConstruct
    protected void init(){
        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //token creation
    public String creatToken(UserDTO userDTO){
        Date now=new Date();
        Date validityDuration=new Date(now.getTime()+36_000);
        return JWT.create()
                .withIssuer(userDTO.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(validityDuration)
                .withClaim("firstName",userDTO.getFirstName())
                .withClaim("lastName",userDTO.getLastName())
                .sign(Algorithm.HMAC256(secretKey));
    }
    //token validation
    public Authentication validateToken(String token){
        Algorithm algorithm=Algorithm.HMAC256(secretKey);
        JWTVerifier verifier =JWT.require(algorithm).build();

        DecodedJWT decodedJWT= verifier.verify(token);
        //use the info in the decoded JWT to create a user DTO
        UserDTO user= UserDTO.builder()
                .email(decodedJWT.getIssuer())
                .firstName(decodedJWT.getClaim("firstName").asString())
                .lastName(decodedJWT.getClaim("lastName").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(
                user,null, Collections.emptyList()
        );
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm=Algorithm.HMAC256(secretKey);
        JWTVerifier verifier =JWT.require(algorithm).build();

        DecodedJWT decodedJWT= verifier.verify(token);

       User user=userReposritory.findByEmail(decodedJWT.getIssuer())
                .orElseThrow(()->new UsernameNotFoundException(HttpStatus.NOT_FOUND.toString()));

        return new UsernamePasswordAuthenticationToken(
                userMapper.toUserDTO(user),null, Collections.emptyList()
        );
    }
}
