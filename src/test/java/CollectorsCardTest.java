import de.upmann.CollectorsCard;
import org.junit.Before;
import org.junit.Test;

public class CollectorsCardTest {

    CollectorsCard collectorsCard;

    @Before
    public void init(){
        this.collectorsCard = new CollectorsCard(99,100,"TestKarte");
    }

    @Test
    public void testDaylyPrice(){
        assert this.collectorsCard.getDailyPrice()== 1090;
    }
    @Test
    public void testQualityFall(){
        for (int i = 0; i < 5; i++) {
            this.collectorsCard.aging();
        }
        assert this.collectorsCard.getQuality() == 0;
    }

}
