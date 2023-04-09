package tech.plausible.design.notifiers;

import tech.plausible.design.NotifierType;

public class SmsNotifierInfo extends NotifierInfo {
    private String smsGatewayUrl;


    public SmsNotifierInfo(NotifierType notifierType, String smsGatewayUrl) {
        super(notifierType);
        this.smsGatewayUrl = smsGatewayUrl;
    }

    public String getSmsGatewayUrl() {
        return smsGatewayUrl;
    }
}
