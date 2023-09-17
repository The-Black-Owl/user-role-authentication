package api.backend.services;

import api.backend.dto.AuthenticationRequest;
import api.backend.dto.UserDTO;
import api.backend.repository.RoleRepository;
import api.backend.repository.UserReposritory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserReposritory userReposritory;
    @Autowired
    private RoleRepository roleRepository;

    public UserDTO login(AuthenticationRequest request) {
        return null;
    }
}
