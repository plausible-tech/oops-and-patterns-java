package tech.plausible.design.notifiers;

import tech.plausible.design.Notifier;
import tech.plausible.design.User;

public class EmailNotifier implements Notifier {
    private EmailNotifierInfo emailNotifierInfo;

    public EmailNotifier(EmailNotifierInfo emailNotifierInfo) {
        this.emailNotifierInfo = emailNotifierInfo;
    }

    @Override
    public void notify(User user, String message) {
        System.out.println("Email Notififier");
    }
}
