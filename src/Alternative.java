import java.util.ArrayList;

/**
 * Created by pawma on 15.03.2017.
 */
public class Alternative {
    public String name;
    public ArrayList<Weight> priorityList = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void addPriority(Weight weight){
        priorityList.add(weight);
    }
}
