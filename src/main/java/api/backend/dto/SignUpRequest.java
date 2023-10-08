package api.backend.dto;

import api.backend.entities.Role;

import java.util.Set;

public record SignUpRequest(String firstName, String lastName, String email, char[] password) {
}
