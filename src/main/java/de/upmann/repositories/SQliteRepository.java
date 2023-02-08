package de.upmann.repositories;

import de.upmann.*;
import de.upmann.exceptions.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class SQliteRepository implements ProductRepository {

    private final Connection connection;
    private final Statement statement;

    public SQliteRepository(String dbName) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        this.statement = connection.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        statement.executeUpdate("create table IF NOT EXISTS products (id varchar PRIMARY KEY, expiryDate INTEGER, quality INTEGER," +
                "  price INTEGER," + "  name VARCHAR, type VARCHAR, age Integer)");
    }


    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select * from products");
            while (rs.next()) {
                if (rs.getString("type").equals("w")) {
                    products.add(new Wine(rs.getString("id"), rs.getInt("quality"),
                            rs.getInt("price"), rs.getString("name"), rs.getInt("age")));
                } else if (rs.getString("type").equals("k")) {
                    products.add(new CollectorsCard(rs.getString("id"), rs.getInt("quality"),
                            rs.getInt("price"), rs.getString("name"), rs.getInt("age")));
                } else if (rs.getString("type").equals("c")) {
                    Calendar expiryDate = Calendar.getInstance();
                    expiryDate.setTimeInMillis(rs.getLong("expiryDate"));
                    products.add(new Cheese(rs.getString("id"), expiryDate, rs.getInt("quality"),
                            rs.getInt("price"), rs.getString("name")));
                }else {
                    Calendar expiryDate = Calendar.getInstance();
                    expiryDate.setTimeInMillis(rs.getLong("expiryDate"));
                    products.add(new Product( expiryDate,rs.getString("id"), rs.getInt("quality"),
                            rs.getInt("price"), rs.getString("name")));
                }
            }

        } catch (SQLException | WineWithLowQualityException | CheeseHasLowQualityException |
                 CheeseHasLessThenFiftyDaysExpiryException | CheeseHasMoreThenHundredyDaysExpiryException |
                 CollectorsCardIsOverQualityException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void removeProduct(Product product) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM products WHERE id =?");
            ps.setString(1, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProducts(ArrayList<Product> products) {
        try {
            PreparedStatement ps = connection.prepareStatement("REPLACE INTO products VALUES (?,?,?,?,?,?,?) ");
            products.forEach(product-> this.writeProductToDB(ps,product));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeProductToDB (PreparedStatement ps , Product product){
        try {
            ps.setString(1, product.getId());
            ps.setLong(2, product.getExpiryDate().getTimeInMillis());
            ps.setInt(3, product.getQuality());
            ps.setInt(4, product.getPrice());
            ps.setString(5, product.getName());
            if (product.getClass() == Wine.class) {
                ps.setString(6, "w");
                ps.setInt(7, ((Wine) product).getAge());

            }
            if (product.getClass() == CollectorsCard.class) {
                ps.setString(6, "k");
                ps.setInt(7, ((CollectorsCard) product).getAge());

            } else if (product.getClass() == Cheese.class) {

                ps.setString(6, "c");
                ps.setInt(7, 0);
            } else {
                ps.setString(6, "n");
                ps.setInt(7, 0);
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(Product product) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?,?,?) ");
            this.writeProductToDB(ps,product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropTable() throws SQLException {
        this.statement.executeUpdate("drop table  IF EXISTS products");
        this.statement.executeUpdate("create table products (id varchar PRIMARY KEY, expiryDate INTEGER, quality INTEGER," +
                "  price INTEGER," + "  name VARCHAR, type VARCHAR, age Integer)");

    }
}
