package de.upmann;

import java.util.ArrayList;
import java.util.Calendar;
//Do stuff or know others, but not both -> Do Stuff Rack class | Know others Repository
public class Rack {

    //Dependency Injection
    private final ProductRepository productRepository;
    private final Calendar today = Calendar.getInstance();

    public Rack(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.printSummary();

    }

    private void printSummary() {
        this.productRepository.getProducts().forEach((product -> System.out.println(product.summary())));
    }

    private void printDayly() {
        this.productRepository.getProducts().forEach((System.out::println));
    }


    private void sortOutExpiredProducts() {
        this.productRepository.getProducts().stream().filter(product -> product.isExpired(today)).toList().
                forEach(this.productRepository::removeProduct);
    }

    public void increasDay() {
        this.today.add(Calendar.DAY_OF_MONTH, 1);
        this.printDayly();
        this.sortOutExpiredProducts();
        ArrayList<Product> products = this.productRepository.getProducts();
        products.forEach(Product::aging);
        this.productRepository.updateProducts(products);
    }

}
