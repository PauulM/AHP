import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by pawma on 14.03.2017.
 */
public class MatrixTest {

    Matrix matrix;

    @Before
    public void setUp() {
        matrix = new Matrix(3, 3);
        matrix.initializeWithInt(1);
        matrix.setValueByIndex(0.75f, 0, 1);
        matrix.setValueByIndex(3f, 0, 2);
        matrix.setValueByIndex(1.333333f, 1, 0);
        matrix.setValueByIndex(2f, 1, 2);
        matrix.setValueByIndex(0.333333f, 2, 0);
        matrix.setValueByIndex(0.5f, 2, 1);
    }

    @Test
    public void getValueTest() {
        Float value = matrix.getValueByIndex(1, 2);
        Assert.assertTrue(value.equals(2f));
    }

    @Test
    public void setValueTest() {
        matrix.setValueByIndex(8f, 0, 0);
        Assert.assertTrue(matrix.getValueByIndex(0, 0).equals(8f));
    }

    @Test
    public void getColTest() {
        ArrayList<Float> expected = new ArrayList<>();
        expected.add(3f);
        expected.add(2f);
        expected.add(1f);
        Assert.assertTrue(expected.equals(matrix.getColByIndex(2)));
    }

    @Test
    public void getRowTest() {
        ArrayList<Float> expected = new ArrayList<>();
        expected.add(1f);
        expected.add(0.75f);
        expected.add(3f);
        Assert.assertTrue(expected.equals(matrix.getRowByIndex(0)));
    }

    @Test
    public void copyTest(){
        Matrix test = matrix.copy();
        Assert.assertTrue(test.getValueByIndex(0,2).equals(3f));
        Assert.assertTrue(test.getValueByIndex(1,2).equals(2f));
    }

    @Test
    public void setRowTest(){
        ArrayList<Float> row = new ArrayList<>();
        row.add(0f);
        row.add(0f);
        row.add(0f);
        matrix.setRowByIndex(0,row);
        Assert.assertTrue(matrix.getValueByIndex(0,0).equals(0f));
    }

    @Test
    public void setColTest(){
        ArrayList<Float> col = new ArrayList<>();
        col.add(9f);
        col.add(9f);
        col.add(9f);
        matrix.setColByIndex(2,col);
        Assert.assertTrue(matrix.getValueByIndex(2,2).equals(9f));
    }

    @Test
    public void printMatrixToSoutTest(){
        matrix.printMatrixToSout();
    }
}