package de.upmann;

import de.upmann.exceptions.CheeseHasLessThenFiftyDaysExpiryException;
import de.upmann.exceptions.CheeseHasLowQualityException;
import de.upmann.exceptions.CheeseHasMoreThenHundredyDaysExpiryException;

import java.util.Calendar;

//Dependency Inversion Principle Neue Produktart wird über Veerbrung realisiert
//Prefer Polymorphism To If/Else or Switch/Case
public class Cheese extends Product{
    public Cheese(String id, Calendar expiryDate, int quality, int price, String name) throws CheeseHasLowQualityException, CheeseHasLessThenFiftyDaysExpiryException, CheeseHasMoreThenHundredyDaysExpiryException {
        super(expiryDate,id, quality, price, name);
        testExpiry(expiryDate, quality);

    }
    public Cheese(Calendar expiryDate, int quality, int price, String name) throws CheeseHasLowQualityException, CheeseHasLessThenFiftyDaysExpiryException, CheeseHasMoreThenHundredyDaysExpiryException {
        super(expiryDate, quality, price, name);
        testExpiry(expiryDate, quality);

    }

    private static void testExpiry(Calendar expiryDate, int quality) throws CheeseHasLowQualityException, CheeseHasLessThenFiftyDaysExpiryException, CheeseHasMoreThenHundredyDaysExpiryException {
        if(quality <30){
            throw new CheeseHasLowQualityException();
        }
        Calendar inFiftyDays = Calendar.getInstance();
        inFiftyDays.add(Calendar.DAY_OF_MONTH, 50);
        if(expiryDate.before(inFiftyDays)){
            throw new CheeseHasLessThenFiftyDaysExpiryException();
        }
        Calendar inHundredDays = Calendar.getInstance();
        inHundredDays.add(Calendar.DAY_OF_MONTH, 100);
        if(expiryDate.after(inHundredDays)){
            throw new CheeseHasMoreThenHundredyDaysExpiryException();
        }
    }

    //Encapsulate Conditionals
    @Override
    public boolean isExpired(Calendar today) {

        return super.isExpired(today)|| super.getQuality() < 30;
    }

    @Override
    public void aging(){
        super.decreaseQuality();
    }
}
