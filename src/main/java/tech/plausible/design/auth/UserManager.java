package tech.plausible.design.auth;

import java.util.Map;

public class UserManager {

    private Map<String, String> headers;

    public UserManager(Map<String, String> headers) {
        this.headers = headers;
    }

    // TODO: THIS IS A BAD EXAMPLE
    // HERE WE HAVE SIMULATED REQUIRED INFO TO BE OBTAINED
    // DURING EXECUTION OF CODE AND FACTORY USING IT TO CREATE
    // OBJECTS. NOT GREAT BUT SIMULATION. BELOW METHOD IS A
    // SIMPLE FACTORY + EXECUTOR
    public String getUser() {
        String cookie = headers.get("cookie");

        UserIdentifier userIdentifier;
        // Simple factory
        if (cookie.endsWith("0")) {
            userIdentifier = new UserAuthenticator();
        } else {
            userIdentifier = new UserIdentifierImpl();
        }

        return userIdentifier.identifyUser(cookie);
    }

    class UserAuthenticator implements UserIdentifier {

        @Override
        public String identifyUser(String info) {
            return "User Is Authenticated and Session Exists On Server";
        }
    }

    class UserIdentifierImpl implements UserIdentifier {

        @Override
        public String identifyUser(String info) {
            return "User is not authenticated and no authenticated session exists but using token could be identified";
        }
    }
}
