package de.upmann;

import java.util.Calendar;

public class Product {

    // Encapsulation -> Guter Standard vorallem wichtig um Seiteneffekte zu verhindern
    private final Calendar expiryDate;
    private int quality;
    //Price is calculated in cents
    private final int price;
    private final String name;

    public Product(Calendar expiryDate, int quality, int price, String name) {
        this.expiryDate = expiryDate;
        this.quality = quality;
        this.price = price;
        this.name = name;
    }

    public int getDailyPrice() {
        return this.price + this.quality*10;
    }


    public String uebersicht(){

        return "Produkt "+this.name+" läuft ab am "+this.expiryDate.toString()+ ",  Grundpreis "+ this.price+
                "€cent,  Qualität "+this.quality;
    }
    @Override
    public String toString(){

        return "Produkt "+this.name+" Preis: "+this.getDailyPrice()+"  Qualität"+this.quality+" "
                +(this.isExpired(Calendar.getInstance())?"Abgelaufen":"Noch gut");
    }



    public void increaseQuality() {
        this.quality++;
    }

    public void decreaseQuality() {
        this.quality--;
    }

    public boolean isExpired(Calendar today) {
        return  this.expiryDate.before(today);
    }
}
