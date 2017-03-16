import java.util.ArrayList;

/**
 * Created by pawma on 15.03.2017.
 */
public class Criterium {
    private String name;
    private ArrayList<Criterium> subCriteriaList = new ArrayList<>();
    private Criterium parentCriterium = null;
    private ArrayList<Weight> weightsList = new ArrayList<>();
    private ArrayList<Alternative> alternativesList = new ArrayList<>();

    public Alternative findAlternativeByName(String name){
        for(Alternative a : alternativesList){
            if(a.getName().equals(name))
                return a;
        }
        return null;
    }

    public Float findWeightValueToByName(String to){
        for(Weight w : weightsList){
            if (w.getTo().equals(to))
                return w.getValue();
        }
        return null;
    }

    public ArrayList<Criterium> getSubCriteriaList() {
        return subCriteriaList;
    }

    public void addWeight(Weight weight){
        weightsList.add(weight);
    }

    public void addAlternative(Alternative alternative){
        alternativesList.add(alternative);
    }

    public void addSubCriteria(Criterium criterium){
        subCriteriaList.add(criterium);
    }

    public void setSubCriteriaList(ArrayList<Criterium> subCriteriaList) {
        this.subCriteriaList = subCriteriaList;
    }

    public Criterium getParentCriterium() {
        return parentCriterium;
    }

    public void setParentCriterium(Criterium parentCriterium) {
        this.parentCriterium = parentCriterium;
    }



    public boolean hasSubcriteria(){
        return !alternativesList.isEmpty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Weight> getWeightsList() {
        return weightsList;
    }

    public void setWeightsList(ArrayList<Weight> weightsList) {
        this.weightsList = weightsList;
    }

    public ArrayList<Alternative> getAlternativesList() {
        return alternativesList;
    }

    public void setAlternativesList(ArrayList<Alternative> alternativesList) {
        this.alternativesList = alternativesList;
    }
}
