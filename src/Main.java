public class Main {

    public static void main(String[] args) {
        XmlData xmlData = new XmlData("example.xml");
        xmlData.initializeCriteriaList();
        Vectors vectors = new Vectors(xmlData);
        vectors.printResultToSout(vectors.start(xmlData.getCriteria()));
    }
}