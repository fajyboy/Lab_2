// Класс 1 (Option): защитная экипировка, которую можно добавить к аренде квадроцикла
public class ProtectiveGear {

    private final String name;   // название не меняется, поэтому final
    private double price;        // цена за 1 час, может меняться через сеттер

    public ProtectiveGear(String name, double price) {
        this.name = name;
        setPrice(price); // используем сеттер, чтобы проверка работала и здесь
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Ошибка: цена экипировки не может быть отрицательной");
            return; // выходим из метода, цену не меняем
        }
        this.price = price;
    }
}
