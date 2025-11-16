package io.ecommerce.group.user_service.service;

import io.ecommerce.group.user_service.domain.entity.SystemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;

    public void login(SystemUser user, String password) {

        boolean emailValidado = user.getMail();

        if (!emailValidado) {
            throw new RuntimeException("Usuário não validado. Por favor, verifique seu e-mail.");
        }

        passwordValidation(user.getPassword(), password);
    }

    private void passwordValidation(final SystemUser systemUser, final String senha) {
        if (!systemUser.loginAuthentication(senha, passwordEncoder)) {
            throw new RuntimeException("Senha inválida.");
        }
    }
}
