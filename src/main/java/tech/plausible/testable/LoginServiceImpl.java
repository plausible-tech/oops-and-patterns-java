package tech.plausible.testable;

public class LoginServiceImpl implements LoginService {
    @Override
    public LoginResult login(String email, String password) {
        final LoginResult loginResult = LoginResult.builder()
                .withResult("Success")
                .build();

        return loginResult;
    }
}
