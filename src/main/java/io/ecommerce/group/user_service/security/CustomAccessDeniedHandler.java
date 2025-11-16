package io.ecommerce.group.user_service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ecommerce.group.user_service.domain.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        Map<String, String> erros = Map.of("permissao",
                "Usuário não possui permissão para acessar este recurso");

        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                403,
                "Acesso negado",
                erros,
                request.getRequestURI()
        );

        String json = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(json);
    }
}
