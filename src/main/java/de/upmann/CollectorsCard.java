package de.upmann;

import de.upmann.exceptions.CollectorsCardIsOverQualityException;

import java.util.Calendar;

public class CollectorsCard extends Product {
    private int age = 0;

    //Sammelkarten Verfallen nicht
    //Sammelkarten steigen aber alle 5 Tage in der Qualität
    //Der Preis der Sammelkarte steigt bis zu einem Wert von 100 danach fällt er wieder auf 0 zurück bis er wieder steigt
    //Eine Sammelkarte hat einen AktuellenPreis
    public CollectorsCard(String id, int quality, int price, String name, int age) throws CollectorsCardIsOverQualityException {
        super(Calendar.getInstance(), id, quality, price, name);
        this.checkQuality(quality);
        this.age = age;
    }

    private void checkQuality(int quality) throws CollectorsCardIsOverQualityException {
        if(quality>=100){
            throw new CollectorsCardIsOverQualityException();
        }
    }

    public CollectorsCard(int quality, int price, String name) {
        super(Calendar.getInstance(), quality, price, name);
    }

    @Override
    public boolean isExpired(Calendar today) {
        return false;
    }

    @Override
    public void aging() {
        age++;
        if (age == 5) {
            age = 0;
            this.increaseQuality();
            if (this.getQuality() >= 100) {
                while (this.getQuality() != 0) {
                    this.decreaseQuality();
                }

            }
        }

    }

    @Override
    public String summary() {

        return "Produkt " + super.getName() + " läuft nicht ab Grundpreis "
                + super.getDailyPrice() + "€cent,  Qualität " + this.getQuality();
    }

    public int getAge() {
        return this.age;
    }
}
