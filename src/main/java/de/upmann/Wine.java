package de.upmann;

import de.upmann.exceptions.WineWithLowQualityException;

import java.util.Calendar;


//Dependency Inversion Principle Neue Produktart wird über Veerbrung realisiert
public class Wine extends Product {
    private final int price;
    private int age;
    //Fail Fast
    public Wine(int quality, int price, String name) throws WineWithLowQualityException {
        super(Calendar.getInstance(), quality, price, name);
        if (quality < 0) {
            throw new WineWithLowQualityException();
        }
        this.price = super.getDailyPrice();
    }

    @Override
    public int getDailyPrice() {
        return this.price;
    }

    @Override
    public boolean isExpired(Calendar today) {
        return false;
    }

    @Override
    public void aging() {
        this.age++;
        if (this.age % 10 == 0) {
            if (this.getQuality() < 50) {
                this.increaseQuality();
            }
        }
    }

    @Override
    public String summary() {

        return "Produkt " + super.getName() + " läuft nicht ab Grundpreis "
                + this.price + "€cent,  Qualität " + this.getQuality();
    }



}
