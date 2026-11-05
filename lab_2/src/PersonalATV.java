// Аренда квадроцикла для физического лица
public class PersonalATV extends ATV {

    public PersonalATV(String name, double basePrice, String tariff) {
        super(name, basePrice, tariff); // вызов конструктора родителя
    }

    public PersonalATV(String name, double basePrice) {
        super(name, basePrice);
    }

    // Расчёт как в части 1: базовая цена * коэффициент тарифа
    @Override
    public double calculateFinalPrice() {
        return getBasePrice() * getTariffCoefficient();
    }
}
