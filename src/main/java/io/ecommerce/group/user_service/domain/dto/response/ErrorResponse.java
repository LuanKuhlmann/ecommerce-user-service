package io.ecommerce.group.user_service.domain.dto.response;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String erro,
        Map<String, String> erros,
        String caminho) {
}
