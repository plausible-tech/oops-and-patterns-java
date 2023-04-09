package tech.plausible.design.notifiers;

import tech.plausible.design.Notifier;
import tech.plausible.design.User;

public class SmsNotifier implements Notifier {
    private SmsNotifierInfo smsNotifierInfo;

    public SmsNotifier(SmsNotifierInfo smsNotifierInfo) {
        this.smsNotifierInfo = smsNotifierInfo;
    }

    @Override
    public void notify(User user, String message) {
        System.out.println("SMS");
    }
}
