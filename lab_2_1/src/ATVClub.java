// Класс 3 (Manager): учёт заказов клуба и точка входа в программу
public class ATVClub {

    // static — одна переменная на всю программу, общая для всех заказов
    private static int totalOrdersCount = 0;
    private static double totalRevenue = 0;

    public static int getTotalOrdersCount() {
        return totalOrdersCount;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    // Фиксация оплаты: только здесь меняются счётчики
    public static void registerPayment(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: сумма оплаты должна быть больше нуля");
            return;
        }
        totalOrdersCount++;
        totalRevenue += amount;
    }

    public static void printGlobalStats() {
        System.out.println();
        System.out.println(" Статистика ATVClub ");
        System.out.println("Всего заказов: " + totalOrdersCount);
        System.out.printf("Общая выручка: %.2f руб.%n", totalRevenue);
        System.out.println("");
    }

    public static void main(String[] args) {
        ProtectiveGear gear = new ProtectiveGear("Шлем и защита", 300);

        ATV atv1 = new ATV("Yamaha Grizzly", 3000);                    // дополнительный конструктор
        ATV atv2 = new ATV("Polaris Sportsman", 3500, "Sport");        // основной конструктор
        ATV atv3 = new ATV("Can-Am Outlander", 4000, "Monster-Track"); // основной конструктор

        // Вариант 1: только тариф
        double price1 = atv1.calculateFinalPrice();
        registerPayment(price1);
        System.out.printf("%s (%s): %.2f руб.%n", atv1.getName(), atv1.getTariff(), price1);

        // Вариант 2: тариф + экипировка на 1 час
        double price2 = atv2.calculateFinalPrice(gear);
        registerPayment(price2);
        System.out.printf("%s (%s) + экипировка: %.2f руб.%n", atv2.getName(), atv2.getTariff(), price2);

        // Вариант 3: тариф + экипировка на 3 часа
        double price3 = atv3.calculateFinalPrice(gear, 3);
        registerPayment(price3);
        System.out.printf("%s (%s) + экипировка на 3 ч: %.2f руб.%n", atv3.getName(), atv3.getTariff(), price3);

        // Проверка валидации: отрицательную цену поставить нельзя
        gear.setPrice(-100);

        printGlobalStats();
    }
}
