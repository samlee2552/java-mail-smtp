package web.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MainEntry {
	
    static final String FROM = "abcd@naver.com";
    static final String FROMNAME = "samlee";
    static final String TO = "abcd@gmail.com";
    static final String SMTP_USERNAME = "abcd@naver.com";
    static final String SMTP_PASSWORD = "******";
    
    
    static final String HOST = "smtp.naver.com";
    static final int PORT = 25;
    
    static final String SUBJECT = "안녕하세요 누구누구입니다";
    
    static final String BODY = String.join(
        System.getProperty("line.separator"),
        "헬로우", "<h1>안녕~</h1>"
    );
    public static void main(String[] args) throws Exception {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY, "text/html;charset=utf-8");
        
        Transport transport = session.getTransport();
        try {
            System.out.println("Sending...");
            
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            transport.close();
        }
    }
}