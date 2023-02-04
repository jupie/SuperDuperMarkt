import de.upmann.Wine;
import de.upmann.exceptions.WineWithLowQualityException;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class WineTest {

    Wine wine;
    @Before
    public void init() throws WineWithLowQualityException {
        this.wine = new Wine(30,1000,"Guter Wein ");
    }

    @Test
    public void testWineQualityException () {
        try {
            new Wine(29,1,"");
        } catch (WineWithLowQualityException e) {
            assert true;
        }
    }

    @Test
    public  void testWinePricing(){
        int price = this.wine.getDailyPrice();
        for (int i = 0; i < 10; i++) {
            this.wine.aging();
            assert price == this.wine.getDailyPrice();
        }
    }

    @Test
    public void testWineNeverExpires(){

        Calendar today = Calendar.getInstance();
        today.add(Calendar.YEAR,1000);
        assert !this.wine.isExpired(today);
    }
    @Test
    public void testWineQualityIncreasesEveryTenDays(){
        for (int i = 0; i < 1000; i++) {
            assert this.wine.getQuality() <=50;
            this.wine.aging();
            assert i != 10 || this.wine.getQuality() == 31;

        }
    }

}
