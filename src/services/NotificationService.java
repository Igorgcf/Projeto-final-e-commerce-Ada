package services;

public class NotificationService {

    public void notifyEmail(String email, String subject, String message) {
        System.out.printf("[EMAIL] Para: %s | Assunto: %s | Mensagem: %s%n", email, subject, message);
    }
}
