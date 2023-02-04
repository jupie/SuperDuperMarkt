import de.upmann.Cheese;
import de.upmann.exceptions.CheeseHasLessThenFiftyDaysExpiryException;
import de.upmann.exceptions.CheeseHasLowQualityException;
import de.upmann.exceptions.CheeseHasMoreThenHundredyDaysExpiryException;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class CheeseTest {

    Cheese cheese;
    @Before
    public void init() throws CheeseHasLowQualityException, CheeseHasLessThenFiftyDaysExpiryException, CheeseHasMoreThenHundredyDaysExpiryException {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH,51);
        this.cheese = new Cheese(expiryDate,30,30,"TestCheese");
    }

    @Test
    public void testCheeseQualityExpiry(){
        assert  !this.cheese.isExpired(Calendar.getInstance());
        this.cheese.decreaseQuality();
        assert  this.cheese.isExpired(Calendar.getInstance());
    }

    @Test
    public void testCheeseLoosesQuality(){
        assert  !this.cheese.isExpired(Calendar.getInstance());
        this.cheese.aging();
        assert  this.cheese.isExpired(Calendar.getInstance());
    }


    @Test
    public void testCheeseWithExpiryDate(){
        try{
            Calendar date  = Calendar.getInstance();
            new Cheese(date, 31,22,"");
        }
         catch (CheeseHasLowQualityException | CheeseHasMoreThenHundredyDaysExpiryException e) {
           assert false;
        } catch (CheeseHasLessThenFiftyDaysExpiryException e) {
           assert true;
        }

        try{
            Calendar date  = Calendar.getInstance();
            date.add(Calendar.MONTH,101);
            new Cheese(date, 31,22,""    );
        }
        catch (CheeseHasLowQualityException |  CheeseHasLessThenFiftyDaysExpiryException e) {
            assert false;
        } catch (CheeseHasMoreThenHundredyDaysExpiryException e) {
            assert true;
        }
        try{
            Calendar date  = Calendar.getInstance();
            date.add(Calendar.MONTH,60);
            new Cheese(date, 28,22,""    );
        }
        catch (CheeseHasMoreThenHundredyDaysExpiryException |  CheeseHasLessThenFiftyDaysExpiryException e) {
            assert false;
        } catch (CheeseHasLowQualityException e) {
            assert true;
        }
    }
}
