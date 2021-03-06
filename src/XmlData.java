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
            this.document = builder.parse(fileName);

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
                    if(parentCriterium != null)
                        parentCriterium.setName(childList.item(i).getTextContent());
                }
                else if(nodeType.equals("weight")){
                    Weight weight = new Weight();
                    Element e = (Element) childList.item(i);
                    weight.setTo(e.getAttribute("to"));
                    weight.setValue(Float.parseFloat(childList.item(i).getTextContent()));
                    if(parentCriterium != null)
                        parentCriterium.addWeight(weight);
                }
                else if(nodeType.equals("alternative")){
                    Alternative alternative = handleAlternative(childList.item(i), parentCriterium);
                    if(parentCriterium != null)
                        parentCriterium.addAlternative(alternative);
                }

            }
        }
    }

    private Alternative handleAlternative(Node parentNode, Criterium parentCriterium){
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

    public ArrayList<Criterium> getCriteria(){
        return this.criteria;
    }

}
