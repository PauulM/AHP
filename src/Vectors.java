import java.util.ArrayList;

/**
 * Created by pawma on 14.03.2017.
 */
public class Vectors {

    public static ArrayList<Float> normalizeVector(ArrayList<Float> vector){
        Float elementsSum = sumElementsOfArray(vector);
        ArrayList<Float> result = new ArrayList<>();
        for(Float tmp : vector){
            result.add(tmp/elementsSum);
        }
        return result;
    }

    public static Float sumElementsOfArray(ArrayList<Float> list){
        float elementsSum = 0f;
        for(Float tmp : list){
            elementsSum += tmp;
        }
        return elementsSum;
    }

    public static ArrayList<Float> computePriorityVectorFromMatrix(Matrix matrix){
        ArrayList<Float> result = new ArrayList<>();
        ArrayList<Float> columnVector;
        ArrayList<Float> rowVector;
        Matrix tmpMatrix = matrix.copy();
        for(int i=0;i<matrix.getCols();i++) {
            columnVector = matrix.getColByIndex(i);
            columnVector = normalizeVector(columnVector);
            tmpMatrix.setColByIndex(i, columnVector);
        }
        //tmpMatrix ma teraz przeskalowane kolumny
        float sum;
        for(int j=0;j<matrix.getRows();j++){
            rowVector = tmpMatrix.getRowByIndex(j);
            sum = sumElementsOfArray(rowVector);
            result.add(sum/(float)matrix.getCols());
        }
        result = normalizeVector(result);
        return result;
    }
}