package de.upmann;

import de.upmann.exceptions.CheeseHasLessThenFiftyDaysExpiryException;
import de.upmann.exceptions.CheeseHasLowQualityException;
import de.upmann.exceptions.CheeseHasMoreThenHundredyDaysExpiryException;
import de.upmann.exceptions.WineWithLowQualityException;
import de.upmann.repositories.CSVRepository;
import de.upmann.repositories.MockUpRepository;

import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CheeseHasLowQualityException, CheeseHasLessThenFiftyDaysExpiryException, CheeseHasMoreThenHundredyDaysExpiryException, WineWithLowQualityException {
        System.out.println("-----------------------------------------");
        System.out.println("------------SuperDuperMarkt--------------");
        System.out.println("---------von Jannik Upmann---------------");
        System.out.println("-----------------------------------------");
        System.out.println("Herzlich Willkommen im SuperDuperMarkt. Bitte wählen Sie jetzt Ihre Datenquelle: ");
        System.out.println("[1/standard]MockUp [2]CSV [3]SQlite");
        Scanner eingabe = new Scanner(System.in);
        int datenquellenNummer = eingabe.nextInt();
        ProductRepository productRepository;
        if (datenquellenNummer == 2) {
            productRepository = new CSVRepository("markt.csv");
            productRepository.updateProducts(new MockUpRepository().getProducts());
        }
        if (datenquellenNummer == 3) {
            productRepository = new CSVRepository("markt.db");
            productRepository.updateProducts(new MockUpRepository().getProducts());
        } else {
            productRepository = new MockUpRepository();
        }
        Rack rack = new Rack(productRepository);

        while (true) {
            System.out.println("Schreiben Sie \"next\" für den nächsten Tag; Schreiben Sie \"add\" " +
                    "um ein neues Zufälliges Produkt einzuräumen;Schreiben Sie \"exit\" um das Programm zu beenden;  ");
            String next = eingabe.next();
            if (next.equals("exit")) {
                System.out.println("bye bye");
                break;
            }
            if (next.equals("next")) {
                rack.increasDay();
            }
            if (next.equals("add")) {
                int rand = new Random().nextInt(3);
                if (rand == 2) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DAY_OF_MONTH, new Random().nextInt(50));
                    rack.addProduct(new Product(calendar, new Random().nextInt(50), new Random().nextInt(50), "added product"));
                }
                if (rand == 1) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DAY_OF_MONTH, 50 + new Random().nextInt(50));
                    rack.addProduct(new Cheese(calendar, new Random().nextInt(50)+30, new Random().nextInt(50), "added Cheese"));
                }
                if (rand == 0) {

                    rack.addProduct(new Wine( new Random().nextInt(50), new Random().nextInt(50), "added Wine"));
                }
                System.out.println("Produkt hinzugefügt");

            }

        }


    }

}
