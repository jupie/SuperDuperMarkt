import de.upmann.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;


public class ProductTest {

    private Product product;

    @Before
    public void init(){
        Calendar date  = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH,5);
        this.product = new Product(date, 0,0,"test");
    }


    @Test
    public void testGetDailyPrice(){
        int price = this.product.getDailyPrice();
        assert price == 200;
    }

    @Test
    public void testIncreaseQuality(){
        this.product.increaseQuality();
        int price = this.product.getDailyPrice();
        assert price == 210;
    }
    @Test
    public void testDecreaseQuality(){
        this.product.decreaseQuality();
        int price = this.product.getDailyPrice();
        assert price == 190;
    }
    @Test
    public void testIfExpired(){

        Calendar date  = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH,-1);
        this.product = new Product(date, 0,0,"test");
        assert this.product.isExpired(Calendar.getInstance());
        date  = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH,5);
        this.product = new Product(date, 0,0,"test");
        assert !this.product.isExpired(Calendar.getInstance());

    }


}
