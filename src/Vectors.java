import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pawma on 14.03.2017.
 */
public class Vectors {

    private HashMap<Integer, String> alternativesIDs;
    //private HashMap<Integer, String> criteriaIDs;

    public Vectors(XmlData xmlData) {
        //alternativesIDs = Vectors.applyIdToAlternatives(xmlData.getCriteria().get(0).getAlternativesList());
        for(Criterium c : xmlData.getCriteria()){
            if(c.getAlternativesList().size() != 0){
                alternativesIDs = Vectors.applyIdToAlternatives(c.getAlternativesList());
            }
        }
        //criteriaIDs = new HashMap<>();
    }

    public static ArrayList<Float> normalizeVector(ArrayList<Float> vector) {
        Float elementsSum = sumElementsOfArray(vector);
        ArrayList<Float> result = new ArrayList<>();
        for (Float tmp : vector) {
            result.add(tmp / elementsSum);
        }
        return result;
    }

    public static Float sumElementsOfArray(ArrayList<Float> list) {
        float elementsSum = 0f;
        for (Float tmp : list) {
            elementsSum += tmp;
        }
        return elementsSum;
    }

    public static ArrayList<Float> computePriorityVectorFromMatrix(Matrix matrix) {
        ArrayList<Float> result = new ArrayList<>();
        ArrayList<Float> columnVector;
        ArrayList<Float> rowVector;
        Matrix tmpMatrix = matrix.copy();
        for (int i = 0; i < matrix.getCols(); i++) {
            columnVector = matrix.getColByIndex(i);
            columnVector = normalizeVector(columnVector);
            tmpMatrix.setColByIndex(i, columnVector);
        }
        //tmpMatrix ma teraz przeskalowane kolumny
        float sum;
        for (int j = 0; j < matrix.getRows(); j++) {
            rowVector = tmpMatrix.getRowByIndex(j);
            sum = sumElementsOfArray(rowVector);
            result.add(sum / (float) matrix.getCols());
        }
        result = normalizeVector(result);
        return result;
    }

    public Matrix createPriorityMatrixFromCriterium(Criterium criterium) {//, HashMap<Integer, String> map){
        Matrix matrix = null;
        try {
            ArrayList<Alternative> alternatives = criterium.getAlternativesList();
            matrix = new Matrix(alternatives.size(), alternatives.size());
            matrix.initializeWithInt(1f);
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getCols(); j++) {
                    if (i == j)
                        continue;
                    String numeratorName = alternativesIDs.get(i);
                    String denominatorName = alternativesIDs.get(j);
                    matrix.setValueByIndex(
                            findPriorityByNumAndDenomNames(numeratorName, denominatorName, criterium), i, j);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public Matrix createWeightMatrixFromSiblingCriteria(ArrayList<Criterium> siblingsCriteria) {
        HashMap<Integer, String> criteriaIDs = Vectors.applyIdToCriteria(siblingsCriteria);
        Matrix matrix = new Matrix(siblingsCriteria.size(), siblingsCriteria.size());
        matrix.initializeWithInt(1f);
        try {
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getCols(); j++) {
                    if (i == j)
                        continue;
                    String numeratorName = criteriaIDs.get(i);
                    String denominatorName = criteriaIDs.get(j);
                    matrix.setValueByIndex(findWeightByNumAndDenomNames(numeratorName,
                            denominatorName, siblingsCriteria), i, j);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    private static Float findWeightByNumAndDenomNames(String num, String denom, ArrayList<Criterium> criteria) {
        for (Criterium c : criteria) {
            if (c.getName().equals(num)) {
                return c.findWeightValueToByName(denom);
            }
        }
        return null;
    }

    private static Float findPriorityByNumAndDenomNames(String num, String denom, Criterium criterium) {
        return criterium.findAlternativeByName(num).findPriorityToByName(denom);
    }

    public static HashMap<Integer, String> applyIdToAlternatives(ArrayList<Alternative> alternatives) {
        HashMap<Integer, String> map = new HashMap<>();
        int c = 0;
        for (Alternative a : alternatives) {
            map.put(c, a.getName());
            c++;
        }
        return map;
    }

    public static HashMap<Integer, String> applyIdToCriteria(ArrayList<Criterium> criteria) {
        HashMap<Integer, String> map = new HashMap<>();
        int c = 0;
        for (Criterium a : criteria) {
            map.put(c, a.getName());
            c++;
        }
        return map;
    }

    private ArrayList<Float> computeFinalVectorFromCriteriumList(ArrayList<Criterium> criteria) {
        ArrayList<ArrayList<Float>> vectorsVector = new ArrayList<>();
        for (Criterium c : criteria) {
            ArrayList<Criterium> subCriteria = c.getSubCriteriaList();
            ArrayList<Float> currentVector;
            if (!subCriteria.isEmpty()) {
                currentVector = computeFinalVectorFromCriteriumList(subCriteria);
            }
            else{
                Matrix priorityMatrix = createPriorityMatrixFromCriterium(c);
                currentVector = computePriorityVectorFromMatrix(priorityMatrix);
            }
            vectorsVector.add(currentVector);
        }
        Matrix weightsMatrix = createWeightMatrixFromSiblingCriteria(criteria);
        ArrayList<Float> prioVec = computePriorityVectorFromMatrix(weightsMatrix);
        return linearCombination(prioVec, vectorsVector);
    }

    public ArrayList<Float> start(ArrayList<Criterium> criteria){
        ArrayList<Float> result = computeFinalVectorFromCriteriumList(findRootCriteria(criteria));
        return result;
    }


    public static ArrayList<Criterium> findRootCriteria(ArrayList<Criterium> criteria) {
        ArrayList<Criterium> rootCriteria = new ArrayList<>();
        for (Criterium c : criteria) {
            if (c.getParentCriterium() == null)
                rootCriteria.add(c);
        }
        return rootCriteria;
    }

    private ArrayList<Float> linearCombination(ArrayList<Float> weightVector, ArrayList<ArrayList<Float>> vectorsVector) {
        ArrayList<Float> result = new ArrayList<>();
        for (int i = 0; i < vectorsVector.get(0).size(); i++) {
            Float toAppend = 0f;
            for (int j = 0; j<vectorsVector.size(); j++){
                toAppend += weightVector.get(j) * vectorsVector.get(j).get(i);
            }
            result.add(toAppend);
        }
        return result;
    }
}