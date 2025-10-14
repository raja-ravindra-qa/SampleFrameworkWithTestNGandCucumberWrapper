package TestNG;

import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Properties;
import java.util.regex.*;

public class OTPGmail {
    @Test
    public static void call() throws Exception {
        String otp=getOtp( "rao083110@gmail.com",  "cwfi usqd rxzp uvus",  "verification code");
   System.out.println(otp);
    }
    public static String getOtp(String email, String appPassword, String subjectKeyword) throws Exception {
        Properties props = new Properties();
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.ssl.enable", "true");
        Session session = Session.getInstance(props);

        Store store = session.getStore("imap");
        store.connect(email, appPassword);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        String otp = "";

        for (Message message : messages) {
            System.out.println("Subject: " + message.getSubject());
            if (message.isMimeType("text/plain")) {
                String body = (String) message.getContent();
                System.out.println("Body: " + body);
                Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
                Matcher matcher = pattern.matcher(body);
                if (matcher.find()) {
                    otp = matcher.group();
                    break;
                }
            } else if (message.isMimeType("multipart/*")) {
                Multipart mp = (Multipart) message.getContent();
                for (int i = 0; i < mp.getCount(); i++) {
                    BodyPart bp = mp.getBodyPart(i);
                    if (bp.isMimeType("text/plain")) {
                        String body = (String) bp.getContent();
                        System.out.println("Body: " + body);
                        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
                        Matcher matcher = pattern.matcher(body);
                        if (matcher.find()) {
                            otp = matcher.group();
                            break;
                        }
                    }
                }
            }

        }

        inbox.close(false);
        store.close();
        System.out.println(otp);
        return otp;
    }
}
