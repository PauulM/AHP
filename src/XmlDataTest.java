import org.junit.Test;
import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * Created by pawma on 13.03.2017.
 */
public class XmlDataTest {

    @Test
    public void test(){
        XmlData xmlData = new XmlData("example.xml");
        xmlData.initializeCriteriaList();
    }

}