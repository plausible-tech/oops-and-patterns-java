package tech.plausible.testable;

import java.util.Objects;

public class Login implements LoginService {
    private final LoginService loginService;

    Login(LoginService loginService) {
        this.loginService = loginService;
    }

    public Login() {
        this(new LoginServiceImpl());
    }

    public LoginResult login(String email, String password) {
        if (validateCredentials(email, password)) {
            return this.loginService.login(email, password);
        }

        return LoginResult.builder().withResult("Invalid credentials. Failed!!!").build();
    }

    private boolean validateCredentials(String email, String password) {
        if (Objects.isNull(email)) {
            return false;
        }

        if (Objects.isNull(password)) {
            return  false;
        }

        return true;
    }
}
