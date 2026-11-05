// Система учёта (Manager): принимает оплату и отправляет чек
public class ATVClub {

    // Статические поля — общая статистика для всех объектов ATVClub
    private static int totalOrdersCount = 0;
    private static double totalRevenue = 0;

    // Поле имеет тип интерфейса: клубу всё равно, SMS это или Email
    private final NotificationService notificationService;

    // Способ уведомлений передаётся снаружи (Dependency Injection)
    public ATVClub(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Регистрирует оплату и отправляет чек
    public void processPayment(ATV atv, String contact, double finalPrice) {
        if (finalPrice <= 0) {
            System.out.println("Предупреждение: сумма оплаты должна быть больше нуля");
            return;
        }
        totalOrdersCount++;
        totalRevenue += finalPrice;

        String message = String.format("Оплата аренды %s (%s): %.2f руб.",
                atv.getName(), atv.getTariff(), finalPrice);
        notificationService.sendNotification(message, contact);
    }

    public static int getTotalOrdersCount() {
        return totalOrdersCount;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static void printGlobalStats() {
        System.out.println();
        System.out.println("Статистика ATVClub");
        System.out.println("Всего заказов: " + totalOrdersCount);
        System.out.printf("Общая выручка: %.2f руб.%n", totalRevenue);
        System.out.println("");
    }

    public static void main(String[] args) {
        ProtectiveGear gear = new ProtectiveGear("Шлем и защита", 300);

        // Клуб №1 отправляет чеки по SMS
        ATVClub smsClub = new ATVClub(new SmsNotification());
        ATV personal = new PersonalATV("Polaris Sportsman", 3500, "Sport");
        double price1 = personal.calculateFinalPrice(gear, 2);
        smsClub.processPayment(personal, "+79161234567", price1);

        // Клуб №2 отправляет чеки по Email
        ATVClub emailClub = new ATVClub(new EmailNotification());
        ATV corporate = new CorporateATV("Can-Am Outlander", 4000, "Monster-Track", 5);
        double price2 = corporate.calculateFinalPrice();
        emailClub.processPayment(corporate, "dementew848@mail.ru", price2);

        // Демонстрация проверок
        System.out.println();
        System.out.println("Проверки");
        ATV wrongPrice = new PersonalATV("Yamaha Grizzly", -500);      // цена станет 100
        ATV wrongTariff = new PersonalATV("Honda Rancher", 2000, "Super"); // неизвестный тариф
        wrongTariff.calculateFinalPrice();
        ATV wrongLicenses = new CorporateATV("Stels Guepard", 3000, "Standard", 0); // станет 1
        corporate.calculateFinalPrice(gear);                            // компаниям без экипировки
        smsClub.processPayment(wrongPrice, "12345", wrongPrice.calculateFinalPrice());     // плохой номер
        emailClub.processPayment(wrongLicenses, "mail-without-at", wrongLicenses.calculateFinalPrice()); // плохой email

        printGlobalStats();
    }
}
