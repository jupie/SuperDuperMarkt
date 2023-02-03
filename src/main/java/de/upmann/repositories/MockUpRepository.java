package de.upmann.repositories;

import de.upmann.Product;
import de.upmann.ProductRepository;

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
