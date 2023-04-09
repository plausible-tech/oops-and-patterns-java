package tech.plausible.design;

import tech.plausible.design.notifiers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class NotifierFactory<T extends NotifierInfo> {
    private Properties prop;

    // TODO: Sample WHEN RESPONSIBILITY WAS PASSED TO CLASSES (BUT AT BOOT TIME)
    // private Map<NotifierType, Notifier> notifierMap;


    // BOOTSTRAPPING
    public NotifierFactory(Properties prop) {
        // PROPERTY VALIDATION OR PROCESS MUST DIE FAST
        this.prop = prop;
        // TODO: Sample WHEN RESPONSIBILITY WAS PASSED TO CLASSES (BUT AT BOOT TIME)
        // this.notifierMap = new HashMap<>();


        // TODO: Sample WHEN RESPONSIBILITY WAS PASSED TO CLASSES (BUT AT BOOT TIME)
        // SUPPOSE WE ARE PASSING PROPS TO CLASS CONSTRUCTOR & PASSING VALIDATION RESPONSIBILITY TO CLASSES
        // this.notifierMap.put(NotifierType.SMS, new SmsNotifier(prop);
        // this.notifierMap.put(NotifierType.EMAIL, new EmailNotifierInfo(prop);

    }

    // TODO: Sample WHEN RESPONSIBILITY WAS PASSED TO CLASSES (BUT AT BOOT TIME)
    // public Notifier getNotifier(NotifierType notifierType) {
    //    return this.notifierMap.getOrDefault(notifierType, null);
    // }

    // RUNTIME
    public Notifier getNotifier(NotifierType notifierType, T info) {
        switch (notifierType) {
            case SMS:
                if(info.getNotifierType() == NotifierType.SMS) {
                    return new SmsNotifier((SmsNotifierInfo) info);
                }
                throw new IllegalArgumentException("");

            case EMAIL:
                if(info.getNotifierType() == NotifierType.EMAIL) {
                    return new EmailNotifier((EmailNotifierInfo) info);
                }
                throw new IllegalArgumentException("");
            default: throw new IllegalStateException("No notifier");
        }
    }
}
