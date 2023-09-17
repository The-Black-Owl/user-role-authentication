package api.backend.dto;

// this is used for field reception from client
public record AuthenticationRequest(String email,char[] password) {
}
