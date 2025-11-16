package io.ecommerce.group.user_service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ecommerce.group.user_service.domain.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        Map<String, String> erros = Map.of("autenticacao", "Usuário não autenticado ou token inválido");

        ErrorResponse respostaErro = new ErrorResponse(
                Instant.now(),
                401,
                "Não autenticado",
                erros,
                request.getRequestURI()
        );

        String json = objectMapper.writeValueAsString(respostaErro);
        response.getWriter().write(json);
    }
}
