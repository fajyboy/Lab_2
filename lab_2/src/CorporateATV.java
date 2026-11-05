// Аренда квадроциклов для компании (юридического лица)
public class CorporateATV extends ATV {

    private final int activeLicensesCount; // сколько квадроциклов берёт компания

    public CorporateATV(String name, double basePrice, String tariff, int activeLicensesCount) {
        super(name, basePrice, tariff); // super(...) всегда первой строкой
        if (activeLicensesCount < 1) {
            System.out.println("Предупреждение: лицензий должно быть не меньше 1, установлено 1");
            activeLicensesCount = 1;
        }
        this.activeLicensesCount = activeLicensesCount;
    }

    // (базовая цена * коэффициент тарифа) * количество лицензий
    @Override
    public double calculateFinalPrice() {
        return getBasePrice() * getTariffCoefficient() * activeLicensesCount;
    }

    // Компаниям экипировка не выдаётся: предупреждаем и возвращаем стандартную цену
    @Override
    public double calculateFinalPrice(ProtectiveGear gear, int hours) {
        System.out.println("Предупреждение: для корпоративных клиентов экипировка не применяется");
        return calculateFinalPrice();
    }

    public int getActiveLicensesCount() {
        return activeLicensesCount;
    }
}
