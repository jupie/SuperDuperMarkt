package de.upmann;

import java.util.ArrayList;

public interface ProductRepository {

    public ArrayList<Product> getProducts();

    public void removeProduct(Product product);

    void updateProducts(ArrayList<Product> products);
}
