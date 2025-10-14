package TestNG;

import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Properties;
import java.util.regex.*;

public class OTPOutlook {

    @Test
    public static void call() throws Exception {
        // Replace with your Outlook email and app password
        String otp = getOtp("rao083110@outlook.com", "zipxvnwhvftlicko", "verification code");
        System.out.println("Extracted OTP: " + otp);
    }

    public static String getOtp(String email, String appPassword, String subjectKeyword) throws Exception {
        Properties props = new Properties();
        props.put("mail.imap.host", "outlook.office365.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.imap.starttls.enable", "true");

        Session session = Session.getInstance(props);
        Store store = session.getStore("imap");
        store.connect(email, appPassword);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // Fetch unread (new) emails
        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        String otp = "";

        for (Message message : messages) {
            String subject = message.getSubject();
            System.out.println("Subject: " + subject);

            if (subject != null && subject.toLowerCase().contains(subjectKeyword.toLowerCase())) {

                if (message.isMimeType("text/plain")) {
                    String body = (String) message.getContent();
                    otp = extractOtp(body);
                    if (!otp.isEmpty()) break;

                } else if (message.isMimeType("multipart/*")) {
                    Multipart mp = (Multipart) message.getContent();
                    for (int i = 0; i < mp.getCount(); i++) {
                        BodyPart bp = mp.getBodyPart(i);
                        if (bp.isMimeType("text/plain")) {
                            String body = (String) bp.getContent();
                            otp = extractOtp(body);
                            if (!otp.isEmpty()) break;
                        }
                    }
                }
            }
        }

        inbox.close(false);
        store.close();

        return otp.isEmpty() ? "No OTP found." : otp;
    }

    private static String extractOtp(String text) {
        System.out.println("Body: " + text);
        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }
}
