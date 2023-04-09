package tech.plausible.design;

import tech.plausible.design.notifiers.EmailNotifierInfo;
import tech.plausible.design.notifiers.SmsNotifier;
import tech.plausible.design.notifiers.SmsNotifierInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Runner {
    public static void main(String[] args) {
        try (InputStream input = Runner.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            final User user = new User("12", "Abc", "a@b.com", "12133211");

            // TODO: Validate prop during boot-process

            SmsNotifierInfo info = new SmsNotifierInfo(NotifierType.SMS, "url");
            NotifierFactory factory = new NotifierFactory(prop);

            final Notifier notifier = factory.getNotifier(NotifierType.SMS, info);
            notifier.notify(user, "Important Message");


//            //load a properties file from class path, inside static method
//            prop.load(input);
//
//            //get the property value and print it out
//            System.out.println(prop.getProperty("notifiers"));
//            System.out.println(prop.getProperty("notifier.fromEmail"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
