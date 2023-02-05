package de.upmann;

import java.util.ArrayList;
//Interface Segregation Principle Zusammen mit ein bisschen Onion Architecture

public interface ProductRepository {

    ArrayList<Product> getProducts();

    void removeProduct(Product product);

    void updateProducts(ArrayList<Product> products);


}
