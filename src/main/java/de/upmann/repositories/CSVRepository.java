package de.upmann.repositories;

import de.upmann.Cheese;
import de.upmann.Product;
import de.upmann.ProductRepository;
import de.upmann.Wine;
import de.upmann.exceptions.CheeseHasLessThenFiftyDaysExpiryException;
import de.upmann.exceptions.CheeseHasLowQualityException;
import de.upmann.exceptions.CheeseHasMoreThenHundredyDaysExpiryException;
import de.upmann.exceptions.WineWithLowQualityException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class CSVRepository implements ProductRepository {

    ArrayList<Product> products;
    File csvFile;

    static String DELIMITER = ",";

    public CSVRepository(String path) {
        this.csvFile = new File(path);
        this.products = this.readFile();
    }


    private void writeFile() {

        try {
            if(!this.csvFile.exists()){
                if(!this.csvFile.createNewFile()){
                    throw new FileAlreadyExistsException("");
                }
            }

            PrintWriter pw = new PrintWriter(this.csvFile);
            this.serializeProducts().forEach(
                    productString-> {
                        pw.println(productString);
                        pw.flush();
                    }
            );

        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht vorhanden");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // id, expiryDate, quality,  price,  name, type
    private ArrayList<String> serializeProducts() {
        ArrayList<String> lines = new ArrayList<>();
        this.products.forEach(product -> {
            String type = this.getProductType(product);
            lines.add(product.getId()+DELIMITER+product.getExpiryDate().getTimeInMillis()
                    +DELIMITER+product.getQuality()
                    +DELIMITER+product.getPrice()
                    +DELIMITER+product.getName()
                    +DELIMITER+type);
        });

        return lines;
    }

    private String getProductType(Product product) {
        if(product.getClass()== Wine.class){
            return "w";
        }
        if(product.getClass()== Cheese.class){
            return "c";
        }
        else{
            return "n";
        }
    }

    private ArrayList<Product> readFile() {
        ArrayList<Product> productRecords = new ArrayList<>();
        try (Scanner scanner = new Scanner(this.csvFile)) {
            while (scanner.hasNextLine()) {
                productRecords.add(this.parse(scanner.nextLine()));
            }
        } catch (FileNotFoundException | WineWithLowQualityException | CheeseHasLowQualityException |
                 CheeseHasLessThenFiftyDaysExpiryException | CheeseHasMoreThenHundredyDaysExpiryException e) {
            System.out.println("CSV Datei könnte beschädigt sein");
        }
        return productRecords;
    }



    private Product parse(String line) throws WineWithLowQualityException, CheeseHasLowQualityException, CheeseHasLessThenFiftyDaysExpiryException, CheeseHasMoreThenHundredyDaysExpiryException {

        String[] values = line.split(DELIMITER);


        String id = values[0];
        long expiry = Long.parseLong(values[1]);
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(expiry);
        int quality = Integer.parseInt(values[2]);
        int price = Integer.parseInt(values[3]);
        String name = values[4];
        if(values.length>5){
            String type = values[5];
            if (type.equals("w")) {
                return new Wine(id, quality, price, name);
            }
            if (type.equals("c")) {
                return new Cheese(id, expiryDate, quality, price, name);
            }
        }


        return new Product(expiryDate, id, quality, price, name);


    }

    @Override
    public ArrayList<Product> getProducts() {
        return this.products;
    }

    @Override
    public void removeProduct(Product product) {
        this.products.remove(product);
        this.writeFile();
    }

    @Override
    public void updateProducts(ArrayList<Product> products) {
        this.products = products;
        this.writeFile();
    }
}
