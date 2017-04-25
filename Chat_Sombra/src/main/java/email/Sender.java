package email;

public interface Sender {

   void send(String subject, String text, String fromEmail, String toEmail);

}
