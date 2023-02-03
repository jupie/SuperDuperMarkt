import de.upmann.Rack;
import de.upmann.repositories.MockUpRepository;
import org.junit.Before;
import org.junit.Test;

public class RackTest {

    private Rack rack;
    private MockUpRepository mockUpRepository;
    @Before
    public void init(){
        this.mockUpRepository = new MockUpRepository();
        this.rack = new Rack(mockUpRepository);
    }

    @Test
    public void testIncreaseDay(){
        for (int i = 1; i < 10; i++) {
            this.rack.increasDay();
            assert this.mockUpRepository.getProducts().size() ==10-i;
        }

    }
}
