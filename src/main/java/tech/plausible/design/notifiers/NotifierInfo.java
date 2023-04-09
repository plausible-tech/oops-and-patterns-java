package tech.plausible.design.notifiers;

import tech.plausible.design.NotifierType;

public class NotifierInfo {
    protected final NotifierType notifierType;

    public NotifierInfo(NotifierType notifierType) {
        this.notifierType = notifierType;
    }

    public NotifierType getNotifierType() {
        return notifierType;
    }
}
