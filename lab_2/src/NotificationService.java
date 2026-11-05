// Интерфейс: контракт для любого способа отправки уведомлений
public interface NotificationService {

    void sendNotification(String message, String recipient);
}
