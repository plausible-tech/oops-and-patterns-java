package tech.plausible.design.auth;

import java.util.HashMap;
import java.util.Map;

// TODO: THIS IS A BAD EXAMPLE
public class DummyController {
//    private UserManager userManager;
//
//    public DummyController(UserManager userManager) {
//        this.userManager = userManager;
//    }

    public String handleAction() {
        // Actual request object and headers
        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", "token-from-cookie-0");

        // TODO: THIS IS A BAD EXAMPLE
        // This is very bad practice as object hierarchy will be created per user request
        UserManager userManager = new UserManager(headers);
        String user = userManager.getUser();
        return user;
    }
}
