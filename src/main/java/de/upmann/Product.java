package de.upmann;

import org.jetbrains.annotations.Contract;

import java.util.Calendar;
import java.util.UUID;


//Singe Responsibility Principle-> Nur die Änderung der Qualität ändert die Klasse
public class Product {

    // Encapsulation -> Guter Standard vorallem wichtig um Seiteneffekte zu verhindern
    private final Calendar expiryDate;

    private final String id;
    private int quality;
    //Price is calculated in cents
    private final int price;
    private final String name;

    public Product(Calendar expiryDate, String id, int quality, int price, String name) {

        this.expiryDate = expiryDate;
        this.id = id;
        this.quality = quality;
        this.price = price;
        this.name = name;
    }

    public Product(Calendar expiryDate, int quality, int price, String name) {
        this.id = UUID.randomUUID().toString();
        this.expiryDate = expiryDate;
        this.quality = quality;
        this.price = price;
        this.name = name;
    }

    public int getDailyPrice() {
        return this.price + this.quality * 10;
    }


    public String summary() {

        return "Produkt " + this.name + " läuft ab am " + this.expiryDate.get(Calendar.DAY_OF_MONTH) + "." + (this.expiryDate.get(Calendar.MONTH) + 1) + "." + this.expiryDate.get(Calendar.YEAR) + ",  Grundpreis " + this.price + "€cent,  Qualität " + this.quality;
    }

    @Override
    public String toString() {

        return "Produkt " + this.name + " Preis: " + this.getDailyPrice() + "  Qualität " + this.quality + " " + (this.isExpired(Calendar.getInstance()) ? "Abgelaufen" : "Noch gut");
    }


    public void increaseQuality() {
        this.quality++;
    }

    public void decreaseQuality() {
        this.quality--;
    }

    public boolean isExpired(Calendar today) {
        return this.expiryDate.before(today);
    }

    public void aging() {
    }

    public int getQuality() {
        return this.quality;
    }

    protected String getName() {
        return this.name;
    }


    //Smart Contracts geben anderen Programmieren die richtige Interpretation des Methodenverhalten vor
    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object product) {
        Product other = (Product) product;
        if (this.id == null || product == null || other.id == null) {
            return false;
        }

        return other.id.equals(this.id);
    }
}
