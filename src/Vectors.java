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


}