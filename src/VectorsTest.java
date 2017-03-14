import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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
}