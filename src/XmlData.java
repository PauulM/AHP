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
    private ArrayList<Criterium> criteria = new ArrayList<>();

    public XmlData(String fileName){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
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

    public void initializeCriteriaList(){
        Node goalNode = document.getElementsByTagName("goal").item(0);
        if (goalNode.getNodeType()==Node.ELEMENT_NODE) {
            handleChildList(goalNode,null);
        }
    }

    private void handleChildList(Node parent, Criterium parentCriterium){
        Element element = (Element) parent;
        NodeList childList = element.getChildNodes();


        for(int i=0; i<childList.getLength(); i++){
            if (childList.item(i).getNodeType()==Node.ELEMENT_NODE){
                String nodeType = childList.item(i).getNodeName();
                if(nodeType.equals("criterium")) {
                    Criterium criterium = new Criterium();
                    if(parentCriterium != null)
                        parentCriterium.addSubCriteria(criterium);
                    criteria.add(criterium);
                    criterium.setParentCriterium(parentCriterium);
                    handleChildList(childList.item(i), criterium);
                }
                else if(nodeType.equals("name")){
                    parentCriterium.setName(childList.item(i).getTextContent());
                }
                else if(nodeType.equals("weight")){
                    Weight weight = new Weight();
                    Element e = (Element) childList.item(i);
                    weight.setTo(e.getAttribute("to"));
                    weight.setValue(Float.parseFloat(childList.item(i).getTextContent()));
                    parentCriterium.addWeight(weight);
                }
                else if(nodeType.equals("alternative")){
                    Alternative alternative = handleAlternative(childList.item(i), parentCriterium);
                    parentCriterium.addAlternative(alternative);
                }

            }
        }
    }

    public Alternative handleAlternative(Node parentNode, Criterium parentCriterium){
        Alternative alternative = new Alternative();
        if(parentNode.getNodeType()==Node.ELEMENT_NODE){
            Element e = (Element) parentNode;
            NodeList childList = e.getChildNodes();
            for(int i=0; i<childList.getLength(); i++){
                if(childList.item(i).getNodeName().equals("name")){
                    alternative.setName(childList.item(i).getTextContent());
                }
                else if(childList.item(i).getNodeName().equals("priority")){
                    Weight weight = new Weight();
                    Element el = (Element) childList.item(i);
                    weight.setTo(el.getAttribute("to"));
                    weight.setValue(Float.parseFloat(childList.item(i).getTextContent()));
                    alternative.addPriority(weight);
                }
            }
        }
        return alternative;
    }














//    public ArrayList<Node> getAllEqualLevelCriteriaToNodeList(int level){
//        NodeList allCriteriaList = document.getElementsByTagName("criterium");
//        ArrayList<Node> result = new ArrayList<>();
//        for(int i=0;i<allCriteriaList.getLength();i++){
//            Node node = allCriteriaList.item(i);
//            if(node.getNodeType()==Node.ELEMENT_NODE){
//                Element element = (Element) node;
//                if(Integer.parseInt(element.getAttribute("level"))==level){
//                    //System.out.println(element.getElementsByTagName("priority").item(0).getTextContent());
//                    result.add(node);
//                }
//            }
//        }
//        return result;
//    }
//
//    public ArrayList<Float> getPriorityVectorFromCriteriaNodeList(ArrayList<Node> list){
//        ArrayList<Float> result = new ArrayList<>();
//        for(Node tmp : list){
//            if(tmp.getNodeType()==Node.ELEMENT_NODE){
//                Element element = (Element) tmp;
//                result.add(Float.parseFloat(element.
//                        getElementsByTagName("priority").item(0).getTextContent()));
//            }
//        }
//        return result;
//    }
//
//
//
//    public void getByTagAndPrintName(String tag){
//        NodeList list = document.getElementsByTagName(tag);
//        for(int i=0; i<list.getLength();i++){
//            if(list.item(i).getNodeType()==Node.ELEMENT_NODE){
//                Element el = (Element) list.item(i);
//                System.out.println(el.getElementsByTagName("name").item(0).getTextContent());
//            }
//
//        }
//    }
}
