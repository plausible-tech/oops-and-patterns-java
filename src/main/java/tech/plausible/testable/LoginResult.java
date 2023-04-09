package tech.plausible.testable;

public class LoginResult {
    private String result;

    public LoginResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String result;

        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public LoginResult build() {
            return new LoginResult(result);
        }
    }
}
