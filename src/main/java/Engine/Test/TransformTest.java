package Engine.Test;

import Engine.Math.Mathf;
import Engine.Math.Vector3;
import Engine.Transform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformTest {
    @Test
    @DisplayName("Transform global position calculation check")
    public void testGetGlobalPosition() {
        Transform t1 = new Transform(), t2 = new Transform(), t3 = new Transform();
        t1.addChild(t2);
        t3.setParent(t2);
        t3.setLocalPosition(new Vector3(0, 0, 1));

        t1.setLocalRotation(new Vector3(0, 30f * Mathf.DEG2RAD, 0));
        t2.setLocalRotation(new Vector3(0, 60f * Mathf.DEG2RAD, 0));
        var globalPosition = t3.getGlobalPosition();
        System.out.println(globalPosition.toString());
        Assertions.assertEquals(1, globalPosition.getX(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, globalPosition.getY(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, globalPosition.getZ(), Mathf.FLOAT_ERROR);

        t2.setLocalPosition(new Vector3(0, -1, 0));
        t1.setLocalRotation(new Vector3(0, 90f * Mathf.DEG2RAD, 0));
        t2.setLocalRotation(new Vector3(0, 90f * Mathf.DEG2RAD, 0));
        globalPosition = t3.getGlobalPosition();
        System.out.println(globalPosition.toString());
        Assertions.assertEquals(0, globalPosition.getX(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(-1, globalPosition.getY(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(-1, globalPosition.getZ(), Mathf.FLOAT_ERROR);

        t1.setLocalPosition(new Vector3(2, -1.5f, 0));
        t2.setLocalPosition(new Vector3(0.25f, 0, 3));
        t1.setLocalRotation(new Vector3(0, 90f * Mathf.DEG2RAD, 0));
        t2.setLocalRotation(new Vector3(90f * Mathf.DEG2RAD, 0, 0));
        globalPosition = t3.getGlobalPosition();
        System.out.println(globalPosition.toString());
        Assertions.assertEquals(5, globalPosition.getX(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(-2.5, globalPosition.getY(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(-0.25f, globalPosition.getZ(), Mathf.FLOAT_ERROR);
    }

    @Test
    @DisplayName("Transform setting global position check")
    public void testSetGlobalPosition() {
        Transform t1 = new Transform(), t2 = new Transform(), t3 = new Transform();
        t3.setParent(t2);
        t1.addChild(t2);
        t3.setLocalPosition(new Vector3(0, 0, 1));

        t1.setLocalRotation(new Vector3(0, 90f * Mathf.DEG2RAD, 0));
        t2.setLocalRotation(new Vector3(0, 90f * Mathf.DEG2RAD, 0));
        t3.setGlobalPosition(new Vector3(0, 0, 0));
        var localPosition = t3.getLocalPosition();
        System.out.println(localPosition.toString());
        Assertions.assertEquals(0, localPosition.getX(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, localPosition.getY(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, localPosition.getZ(), Mathf.FLOAT_ERROR);

        t1.setLocalPosition(new Vector3(0, 0, 0));
        t2.setLocalPosition(new Vector3(0, 0, 0));
        t3.setLocalPosition(new Vector3(0, 0, 1));
        t1.setLocalRotation(new Vector3(90f * Mathf.DEG2RAD, 90f * Mathf.DEG2RAD, 0));
        t2.setLocalRotation(new Vector3(90f * Mathf.DEG2RAD, 0, 0));
        t3.setGlobalPosition(new Vector3(0, 0, 0));
        localPosition = t3.getLocalPosition();
        System.out.println(localPosition.toString());
        Assertions.assertEquals(0, localPosition.getX(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, localPosition.getY(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, localPosition.getZ(), Mathf.FLOAT_ERROR);

        t1.setLocalPosition(new Vector3(0, 1, 0));
        t2.setLocalPosition(new Vector3(1, 0, 0));
        t3.setLocalPosition(new Vector3(0, 0, 1));
        t1.setLocalRotation(new Vector3(0, 90f * Mathf.DEG2RAD, 0));
        t2.setLocalRotation(new Vector3(90f * Mathf.DEG2RAD, 0, 0));
        t3.setGlobalPosition(new Vector3(0, 0, 0));
        localPosition = t3.getLocalPosition();
        System.out.println(localPosition.toString());
        Assertions.assertEquals(-1, localPosition.getX(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, localPosition.getY(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(1, localPosition.getZ(), Mathf.FLOAT_ERROR);
    }
}
