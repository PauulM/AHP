import java.util.ArrayList;
import java.util.HashMap;

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

    public static Matrix createMatrixFromCriterium(Criterium criterium, HashMap<Integer, String> map){
        Matrix matrix = null;
        try {
            ArrayList<Alternative> alternatives = criterium.getAlternativesList();
            matrix = new Matrix(alternatives.size(), alternatives.size());
            matrix.initializeWithInt(1f);
//            HashMap<Integer, String> map = new HashMap<>();
//            int c = 0;
//            for(Alternative a : alternatives){
//                map.put(c, a.getName());
//                c++;
//            }
            for(int i=0; i<matrix.getRows(); i++){
                for(int j=0; j<matrix.getCols(); j++){
                    if(i==j)
                        continue;
                    String numeratorName = map.get(i);
                    String denominatorName = map.get(j);
                    matrix.setValueByIndex(
                            findPriorityByNumAndDenomNames(numeratorName,denominatorName,criterium) , i, j);
                }
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return matrix;
    }

    private static Float findPriorityByNumAndDenomNames(String num, String denom, Criterium criterium){
        return criterium.findAlternativeByName(num).findPriorityToByName(denom);
    }

    public static HashMap<Integer, String> applyIdToAlternatives(ArrayList<Alternative> alternatives){
        HashMap<Integer, String> map = new HashMap<>();
        int c = 0;
        for(Alternative a : alternatives){
            map.put(c, a.getName());
            c++;
        }
        return map;
    }
}