import de.upmann.Product;
import de.upmann.repositories.CSVRepository;
import de.upmann.repositories.MockUpRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class CSVRepositoryTest {

    CSVRepository csvRepository;
    MockUpRepository mockUpRepository;

    private final String fileName = "test.csv";

    @Before
    public void init() {
        this.csvRepository = new CSVRepository(fileName);
        this.mockUpRepository = new MockUpRepository();
    }

    @Test
    public void testUpdate() {
        this.csvRepository.updateProducts(mockUpRepository.getProducts());
        File file = new File(fileName);
        assert file.exists();
    }

    @Test
    public void testReadCSV() {
        this.csvRepository.updateProducts(mockUpRepository.getProducts());
        this.csvRepository = new CSVRepository(fileName);
        this.csvRepository.getProducts().forEach(product -> {
            assert this.mockUpRepository.getProducts().stream().filter(product::equals).count() == 1;

        });
        this.mockUpRepository.getProducts().forEach(product -> {
            assert this.csvRepository.getProducts().stream().filter(product::equals).count() == 1;

        });

    }

    @Test
    public void testRemoveObjectFromCSV() {

        this.csvRepository.updateProducts(mockUpRepository.getProducts());

        Product objectToRemove = this.csvRepository.getProducts().get(2);
        this.csvRepository.removeProduct(objectToRemove);
        this.csvRepository = new CSVRepository(fileName);
        assert this.csvRepository.getProducts().stream().noneMatch(product -> product.equals(objectToRemove));


    }

}
