// Отправка уведомлений по электронной почте
public class EmailNotification implements NotificationService {

    @Override
    public void sendNotification(String message, String recipient) {
        // Проверка адреса: должны быть символ @ и точка
        if (!recipient.contains("@") || !recipient.contains(".")) {
            System.out.println("Предупреждение: некорректный email \"" + recipient + "\", письмо не отправлено");
            return;
        }
        System.out.println("[EMAIL] На адрес " + recipient + " отправлено письмо: " + message);
    }
}
