package de.upmann;

import de.upmann.repositories.CSVRepository;
import de.upmann.repositories.MockUpRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("------------SuperDuperMarkt--------------");
        System.out.println("---------von Jannik Upmann---------------");
        System.out.println("-----------------------------------------");
        System.out.println("Herzlich Willkommen im SuperDuperMarkt. Bitte wählen Sie jetzt Ihre Datenquelle: ");
        System.out.println("[1/standard]MockUp [2]CSV [3]SQlite");
        Scanner eingabe = new Scanner(System.in);
        int datenquellenNummer = eingabe.nextInt();
        ProductRepository productRepository;
        if(datenquellenNummer == 2){
            productRepository = new CSVRepository("markt.csv");
            productRepository.updateProducts(new MockUpRepository().getProducts());
        }
        else {
            productRepository = new MockUpRepository();
        }
        Rack rack = new Rack(productRepository);

        while (true){
            System.out.println("Schreiben Sie \"next\" für den nächsten Tag");
            String next = eingabe.next();
            if(next.equals("exit")){
                break;
            }
            if(next.equals("next")){
                rack.increasDay();
            }



        }

    }
}