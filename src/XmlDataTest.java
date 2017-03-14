import org.junit.Test;

/**
 * Created by pawma on 13.03.2017.
 */
public class XmlDataTest {

    @Test
    public void test(){
        XmlData xmlData = new XmlData("example.xml");
        xmlData.getByTagAndPrintName("criterium");
    }

}