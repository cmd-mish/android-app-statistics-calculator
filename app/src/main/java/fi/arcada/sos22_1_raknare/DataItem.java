package fi.arcada.sos22_1_raknare;

public class DataItem {
    // Attribut / instansvariablerna
    private String name;
    private double value;

    // Kostruktormetod
    public DataItem(String name, double value) {
        this.name = name;
        this.value = value;
    }

    // Getter-metoder
    public String getName() { return name; }
    public double getValue() { return value; }

    // Setter-metod för värden
    public void setValue(double value) { this.value = value; }

}
