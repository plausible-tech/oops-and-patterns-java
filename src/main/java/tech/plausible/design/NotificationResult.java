package tech.plausible.design;

public class NotificationResult {
    private String result;

    public NotificationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static class Builder {
        private String result;

        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public NotificationResult build() {
            return new NotificationResult(result);
        }
    }
}
