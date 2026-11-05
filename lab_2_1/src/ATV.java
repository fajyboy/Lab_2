// Класс 2 (Target): квадроцикл, для которого считается стоимость аренды
public class ATV {

    // Коэффициенты тарифов из варианта 4
    private static final double STANDARD_COEF = 1.0;       // Стандарт: +0%
    private static final double SPORT_COEF = 1.5;          // Спорт: +50%
    private static final double MONSTER_TRACK_COEF = 2.1;  // Monster-Track: +110%

    private final String name;
    private final double basePrice;
    private final String tariff;
    private ProtectiveGear appliedOption = null; // по умолчанию экипировки нет

    // Основной конструктор: принимает все параметры
    public ATV(String name, double basePrice, String tariff) {
        this.name = name;
        this.basePrice = basePrice;
        this.tariff = tariff;
    }

    // Дополнительный конструктор: тариф по умолчанию "Standard"
    public ATV(String name, double basePrice) {
        this(name, basePrice, "Standard"); // вызываем основной конструктор
    }

    // Вариант 1: цена квадроцикла с учётом тарифа
    public double calculateFinalPrice() {
        return basePrice * getTariffCoefficient();
    }

    // Вариант 2: цена квадроцикла + экипировка на 1 час
    public double calculateFinalPrice(ProtectiveGear gear) {
        return calculateFinalPrice(gear, 1);
    }

    // Вариант 3: цена квадроцикла + экипировка на несколько часов
    public double calculateFinalPrice(ProtectiveGear gear, int hours) {
        if (hours < 1) {
            System.out.println("Ошибка: часов должно быть не меньше 1, считаем за 1 час");
            hours = 1;
        }
        appliedOption = gear; // запоминаем, какая экипировка выдана
        return calculateFinalPrice() + gear.getPrice() * hours;
    }

    // Коэффициент по тарифу (switch со стрелками, без break)
    private double getTariffCoefficient() {
        return switch (tariff) {
            case "Standard" -> STANDARD_COEF;
            case "Sport" -> SPORT_COEF;
            case "Monster-Track" -> MONSTER_TRACK_COEF;
            default -> STANDARD_COEF; // неизвестный тариф считаем стандартным
        };
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getTariff() {
        return tariff;
    }

    public ProtectiveGear getAppliedOption() {
        return appliedOption;
    }
}
