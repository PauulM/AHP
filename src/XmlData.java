/**
 * Created by pawma on 13.03.2017.
 */
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class XmlData {

    private Document document;

    public XmlData(String fileName){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.parse("example.xml");
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getByTagAndPrintName(String tag){
        NodeList list = document.getElementsByTagName(tag);
        for(int i=0; i<list.getLength();i++){
            if(list.item(i).getNodeType()==Node.ELEMENT_NODE){
                Element el = (Element) list.item(i);
                System.out.println(el.getElementsByTagName("name").item(0).getTextContent());
            }

        }
    }
}
