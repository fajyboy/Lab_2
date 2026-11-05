// Отправка уведомлений по SMS
public class SmsNotification implements NotificationService {

    private static final int MIN_PHONE_LENGTH = 11;

    @Override
    public void sendNotification(String message, String recipient) {
        // Проверка номера: начинается с +7 или 8 и не короче 11 символов
        boolean correctStart = recipient.startsWith("+7") || recipient.startsWith("8");
        if (!correctStart || recipient.length() < MIN_PHONE_LENGTH) {
            System.out.println("Предупреждение: некорректный номер \"" + recipient + "\", SMS не отправлено");
            return;
        }
        System.out.println("[SMS] На номер " + recipient + " отправлено сообщение: " + message);
    }
}
