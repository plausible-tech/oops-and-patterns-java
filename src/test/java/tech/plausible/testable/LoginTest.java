package tech.plausible.testable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoginTest {

    private static final String FAILED_RESULT = "Invalid credentials. Failed!!!";
    private LoginService loginServiceMock;
    private LoginResult failedLoginResult;
    private LoginResult loginResultMock;

    private Login login;

    @Before
    public void before() {
        loginServiceMock = mock(LoginService.class);
        login = new Login(loginServiceMock);
        failedLoginResult = LoginResult.builder().withResult("dwdds").build();
        loginResultMock = mock(LoginResult.class);
    }

    @Test
    public void test_login_should_fail_for_null_email() {
        final LoginResult result = login.login(null, "sdadsadasdas");
        assertEquals(result.getResult(), FAILED_RESULT);
    }

    @Test
    public void test_login_should_fail_for_null_password() {
        final LoginResult result = login.login("sdadsadasdas", null);
        assertEquals(result.getResult(), FAILED_RESULT);
    }

    @Test
    public void test_should_pass_service_result() {
        when(loginServiceMock.login(Mockito.anyString(), Mockito.anyString())).thenReturn(loginResultMock);
        final LoginResult result = login.login("sdadsadasdas", "dsffsfsdf");

        assertEquals(result, loginResultMock);
    }
}
