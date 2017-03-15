/**
 * Created by pawma on 13.03.2017.
 */
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

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

    public ArrayList<Node> getAllEqualLevelCriteriaToNodeList(int level){
        NodeList allCriteriaList = document.getElementsByTagName("criterium");
        ArrayList<Node> result = new ArrayList<>();
        for(int i=0;i<allCriteriaList.getLength();i++){
            Node node = allCriteriaList.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element element = (Element) node;
                if(Integer.parseInt(element.getAttribute("level"))==level){
                    //System.out.println(element.getElementsByTagName("priority").item(0).getTextContent());
                    result.add(node);
                }
            }
        }
        return result;
    }

    public ArrayList<Float> getPriorityVectorFromCriteriaNodeList(ArrayList<Node> list){
        ArrayList<Float> result = new ArrayList<>();
        for(Node tmp : list){
            if(tmp.getNodeType()==Node.ELEMENT_NODE){
                Element element = (Element) tmp;
                result.add(Float.parseFloat(element.
                        getElementsByTagName("priority").item(0).getTextContent()));
            }
        }
        return result;
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
