package api.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String header=request.getHeader(HttpHeaders.AUTHORIZATION);
        //check if the authorization header exist
        if (header!=null){
            String[] authenticationElements= header.split(" ");
            if ( authenticationElements.length==2 && "Bearer".equals(authenticationElements[0])){
                try{
                    if ("GET".equals(request.getMethod())){
                    SecurityContextHolder.getContext()
                            .setAuthentication(userAuthProvider.validateToken(authenticationElements[1]));
                    }else{
                        SecurityContextHolder.getContext()
                                .setAuthentication(userAuthProvider.validateTokenStrongly(authenticationElements[1]));
                    }
                }catch (RuntimeException e){
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
