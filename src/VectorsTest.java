import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by pawma on 14.03.2017.
 */
public class VectorsTest {

    Float eps = 0.001f;

    @Test
    public void normalizeVectorTest(){
        ArrayList<Float> list = new ArrayList<>();
        list.add(1f);
        list.add(1.333333f);
        list.add(0.333333f);
        ArrayList<Float> normVec = Vectors.normalizeVector(list);
        Assert.assertTrue(Math.abs(normVec.get(0) - 0.375f) < eps);
        Assert.assertTrue(Math.abs(normVec.get(1) - 0.5f) < eps);
        Assert.assertTrue(Math.abs(normVec.get(2) - 0.125f) < eps);
    }

    @Test
    public void computePriorityVectorFromMatrixTest_DataSet1(){
        Matrix matrix = new Matrix(3, 3);
        matrix.initializeWithInt(1);
        matrix.setValueByIndex(0.75f, 0, 1);
        matrix.setValueByIndex(3f, 0, 2);
        matrix.setValueByIndex(1.333333f, 1, 0);
        matrix.setValueByIndex(2f, 1, 2);
        matrix.setValueByIndex(0.333333f, 2, 0);
        matrix.setValueByIndex(0.5f, 2, 1);

        ArrayList<Float> vector = Vectors.computePriorityVectorFromMatrix(matrix);
        for(Float tmp : vector){
            System.out.print(tmp + ", ");
        }
    }

    @Test
    public void computePriorityVectorFromMatrixTest_DataSet2(){
        Matrix matrix = new Matrix(4, 4);
        matrix.initializeWithInt(1);
        matrix.setValueByIndex(0.5f, 0, 1);
        matrix.setValueByIndex(0.25f, 0, 2);
        matrix.setValueByIndex(3f, 0, 3);
        matrix.setValueByIndex(2f, 1, 0);
        matrix.setValueByIndex(0.5f, 1, 2);
        matrix.setValueByIndex(2f, 1, 3);
        matrix.setValueByIndex(4f, 2, 0);
        matrix.setValueByIndex(2f, 2, 1);
        matrix.setValueByIndex(2f, 2, 3);
        matrix.setValueByIndex(0.333333f, 3, 0);
        matrix.setValueByIndex(0.5f, 3, 1);
        matrix.setValueByIndex(0.5f, 3, 2);

        ArrayList<Float> vector = Vectors.computePriorityVectorFromMatrix(matrix);
        for(Float tmp : vector){
            System.out.print(tmp + ", ");
        }
    }

    @Test
    public void computePriorityVectorFromMatrixTest_DataSet3(){
        Matrix matrix = new Matrix(3, 3);
        matrix.initializeWithInt(1);
        matrix.setValueByIndex(0.5f, 0, 1);
        matrix.setValueByIndex(0.25f, 0, 2);
        matrix.setValueByIndex(2f, 1, 0);
        matrix.setValueByIndex(0.5f, 1, 2);
        matrix.setValueByIndex(4f, 2, 0);
        matrix.setValueByIndex(2f, 2, 1);

        ArrayList<Float> vector = Vectors.computePriorityVectorFromMatrix(matrix);
        for(Float tmp : vector){
            System.out.print(tmp + ", ");
        }
    }

    @Test
    public void createMatrixFromCriteriumTest(){
        XmlData xmlData = new XmlData("example.xml");
        xmlData.initializeCriteriaList();

        //WAZNE ZEBY PRZED ROZPOCZECIEM OBLICZEN USTALIC ID ALTERNATYW
        //KOLEJNOSC KONCOWA ZOSTANIE USTALONA NA PODSTAWIE KOLEJNOSCI W XMLU DLA PIERWSZEGO KRYTERIUM
        HashMap<Integer, String> ids = Vectors.applyIdToAlternatives(xmlData.getCriteria().get(3).getAlternativesList());
        Vectors.createMatrixFromCriterium(xmlData.getCriteria().get(4), ids);
    }
}