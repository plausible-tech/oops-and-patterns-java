package tech.plausible.design.notifiers;

import tech.plausible.design.NotifierType;

public class EmailNotifierInfo  extends NotifierInfo {
    private String smtpServerUri;
    private String fromEmail;

    public EmailNotifierInfo(NotifierType notifierType, String smtpServerUri, String fromEmail) {
        super(notifierType);
        this.fromEmail = fromEmail;
        this.smtpServerUri = smtpServerUri;
    }

    public String getSmtpServerUri() {
        return smtpServerUri;
    }

    public String getFromEmail() {
        return fromEmail;
    }
}
