package api.backend.dto;

import java.util.Set;

public record ProductRequest(
        String productName,
        Long SKU,
        String productDescription,
        String category) {
}
