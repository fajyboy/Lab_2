// Абстрактный класс (Target): общий шаблон аренды квадроцикла.
// Объект ATV создать нельзя, только его наследников.
public abstract class ATV {

    // Коэффициенты тарифов из варианта 4
    private static final double STANDARD_COEF = 1.0;       // Стандарт: +0%
    private static final double SPORT_COEF = 1.5;          // Спорт: +50%
    private static final double MONSTER_TRACK_COEF = 2.1;  // Monster-Track: +110%

    private static final double MIN_PRICE = 100.0; // цена, если передали некорректную

    private final String name;
    private final double basePrice;
    private final String tariff;
    private ProtectiveGear appliedOption = null; // по умолчанию экипировки нет

    // Основной конструктор: принимает все параметры
    public ATV(String name, double basePrice, String tariff) {
        if (basePrice <= 0) {
            System.out.println("Предупреждение: цена " + basePrice + " некорректна, установлена " + MIN_PRICE);
            basePrice = MIN_PRICE;
        }
        this.name = name;
        this.basePrice = basePrice;
        this.tariff = tariff;
    }

    // Дополнительный конструктор: тариф по умолчанию "Standard"
    public ATV(String name, double basePrice) {
        this(name, basePrice, "Standard");
    }

    // Абстрактный метод: у каждого наследника свой расчёт цены
    public abstract double calculateFinalPrice();

    // Цена + экипировка на 1 час
    public double calculateFinalPrice(ProtectiveGear gear) {
        return calculateFinalPrice(gear, 1);
    }

    // Цена + экипировка на несколько часов.
    // calculateFinalPrice() здесь вызовется у конкретного наследника (полиморфизм)
    public double calculateFinalPrice(ProtectiveGear gear, int hours) {
        if (hours < 1) {
            System.out.println("Предупреждение: часов должно быть не меньше 1, считаем за 1 час");
            hours = 1;
        }
        appliedOption = gear;
        return calculateFinalPrice() + gear.getPrice() * hours;
    }

    // Коэффициент по тарифу. protected — доступен наследникам
    protected double getTariffCoefficient() {
        return switch (tariff) {
            case "Standard" -> STANDARD_COEF;
            case "Sport" -> SPORT_COEF;
            case "Monster-Track" -> MONSTER_TRACK_COEF;
            default -> {
                System.out.println("[ADMIN] Внимание: неизвестный тариф \"" + tariff + "\", применён Standard");
                yield STANDARD_COEF; // yield возвращает значение из многострочной ветки
            }
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
