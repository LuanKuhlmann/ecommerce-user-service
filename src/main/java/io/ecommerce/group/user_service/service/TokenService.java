package io.ecommerce.group.user_service.service;

import io.ecommerce.group.user_service.Util.ExtractNameInitialsUtil;
import io.ecommerce.group.user_service.domain.dto.response.TokenResponse;
import io.ecommerce.group.user_service.domain.entity.SystemUser;
import io.ecommerce.group.user_service.domain.entity.Token;
import io.ecommerce.group.user_service.domain.enums.TokenType;
import io.ecommerce.group.user_service.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final TokenRepository repository;

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${jwt.expires-access}")
    private Long expireInAccess;

    @Value("${jwt.expires-refresh}")
    private Long expireInRefresh;

    @Value("${jwt.expires-refresh-long}")
    private Long expireInRefreshLong;

    public TokenResponse generateAccessToken(final SystemUser systemUser, final boolean stayConnected) {
        return gerarToken(systemUser, TokenType.ACCESS, expireInAccess, stayConnected);
    }

    public TokenResponse generateRefreshToken(final SystemUser systemUser, final boolean stayConnected) {
        if (stayConnected) {
            return gerarToken(systemUser, TokenType.REFRESH, expireInRefreshLong, true);
        } else {
            return gerarToken(systemUser, TokenType.REFRESH, expireInRefresh, false);
        }
    }

    private TokenResponse gerarToken(final SystemUser systemUser, final TokenType type, long expireInSeconds, final boolean stayConnected) {
        Instant now = Instant.now();
        Instant expiresAt = now.plusSeconds(expireInSeconds);
        String jti = UUID.randomUUID().toString();

        JwtClaimsSet claims = buildClaims(systemUser, type, jti, now, expiresAt);
        String tokenValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        salvarToken(jti, type, systemUser, stayConnected);

        return new TokenResponse(tokenValue, expireInSeconds);
    }

    private JwtClaimsSet buildClaims(final SystemUser systemUser, final TokenType type, final String jti, final Instant now, final Instant expiresAt) {
        JwtClaimsSet.Builder builder = JwtClaimsSet.builder()
                .id(jti)
                .issuer(issuer)
                .subject(systemUser.getId().toString())
                .issuedAt(now)
                .expiresAt(expiresAt);

        if (type == TokenType.ACCESS) {
            addPersonalData(builder, systemUser);
            addRoles(builder, systemUser);
        }

        return builder.build();
    }

    private void addPersonalData(JwtClaimsSet.Builder builder, SystemUser systemUser) {
        builder.claim("userName", systemUser.getName())
                .claim("nameInitials", ExtractNameInitialsUtil.extractInitials(systemUser.getName()));
    }

    private void addRoles(JwtClaimsSet.Builder builder, final SystemUser systemUser) {
        List<String> roles = systemUser.getRoles().stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());

        builder.claim("roles", roles);
    }


    private void salvarToken(final String jti, final TokenType tokenType, final UUID userId, final boolean stayConnected) {
        Token token = Token.builder()
                .identifier(jti)
                .type(tokenType.name())
                .userId(userId)
                .stayConnected(stayConnected)
                .build();

        tokenRepository.save(token);
    }
}
