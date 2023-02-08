import de.upmann.Product;
import de.upmann.repositories.MockUpRepository;
import de.upmann.repositories.SQliteRepository;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Calendar;

public class SQliteRepositoryTest {
    SQliteRepository sQliteRepository;
    MockUpRepository mockUpRepository;

    @Before
    public void init() throws SQLException {
        String dbName = "test";
        this.sQliteRepository = new SQliteRepository(dbName);
        this.sQliteRepository.dropTable();
        this.mockUpRepository = new MockUpRepository();
    }

    @Test
    public void testReadFROMSQL() {
        this.sQliteRepository.updateProducts(mockUpRepository.getProducts());
        this.sQliteRepository.getProducts().forEach(product -> {
            assert this.mockUpRepository.getProducts().stream().filter(product::equals).count() == 1;

        });
        this.mockUpRepository.getProducts().forEach(product -> {
            assert this.sQliteRepository.getProducts().stream().filter(product::equals).count() == 1;

        });

    }

    @Test
    public void testRemoveObjectFromSQL() {

        this.sQliteRepository.updateProducts(mockUpRepository.getProducts());

        Product objectToRemove = this.sQliteRepository.getProducts().get(2);
        this.sQliteRepository.removeProduct(objectToRemove);
        assert this.sQliteRepository.getProducts().stream().noneMatch(product -> product.equals(objectToRemove));


    }

    @Test
    public void testAddProduct() {
        this.sQliteRepository.addProduct(new Product(Calendar.getInstance(), 1, 1, "addedProduct"));
        assert this.sQliteRepository.getProducts().stream().anyMatch(product -> product.getName().equals("addedProduct"));
        this.sQliteRepository.getProducts().forEach(product -> {
            if (product.getName().equals("addedProduct")) {
                this.mockUpRepository.removeProduct(product);
            }
        });
    }


}
