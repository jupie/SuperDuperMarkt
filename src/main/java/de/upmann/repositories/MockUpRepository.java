package de.upmann.repositories;

import de.upmann.Cheese;
import de.upmann.Product;
import de.upmann.ProductRepository;
import de.upmann.Wine;
import de.upmann.exceptions.CheeseHasLessThenFiftyDaysExpiryException;
import de.upmann.exceptions.CheeseHasLowQualityException;
import de.upmann.exceptions.CheeseHasMoreThenHundredyDaysExpiryException;
import de.upmann.exceptions.WineWithLowQualityException;

import java.util.ArrayList;
import java.util.Calendar;

public class MockUpRepository implements ProductRepository {

    ArrayList<Product> products = new ArrayList<>();

    public MockUpRepository(){

        Calendar date;
        for (int i = 0; i < 10; i++) {
            date = Calendar.getInstance();
            date.add(Calendar.DAY_OF_MONTH,i);
            products.add(new Product(date,i,i,"MockUpProduct"+i));
        }

        for (int i = 0; i < 5; i++) {
            date = Calendar.getInstance();
            date.add(Calendar.DAY_OF_MONTH,51+i);
            try {
                products.add(new Cheese(date,40+i,100,"Käse"+i));
            } catch (CheeseHasLowQualityException | CheeseHasLessThenFiftyDaysExpiryException |
                     CheeseHasMoreThenHundredyDaysExpiryException e) {
                System.out.println("Probleme mit Käse");
            }
        }

        for (int i = 0; i < 5; i++) {

            try {
                products.add(new Wine(30+i,100,"Wein"+i));
            } catch (WineWithLowQualityException e) {
                System.out.println("Probleme mit Wein");
            }
        }


    }
    @Override
    public ArrayList<Product> getProducts() {
        return this.products;
    }

    @Override
    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    @Override
    public void updateProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
