package Engine.Test;

import Engine.Math.Mathf;
import Engine.Math.Matrix3x3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Matrix3x3Test {
    @Test
    public void testMul() {
        Matrix3x3 m1 = Matrix3x3.one, m2 = new Matrix3x3();
        m2.getData()[0][1] = 1;
        m2.getData()[1][1] = 1;
        m2.getData()[2][1] = 1;
        m2.getData()[2][2] = 1;

        var res = m1.mul(m2);
        System.out.println(res.toString());
        Assertions.assertEquals(0, res.getData()[0][0], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(1, res.getData()[0][1], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, res.getData()[0][2], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, res.getData()[1][0], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(1, res.getData()[1][1], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, res.getData()[1][2], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, res.getData()[2][0], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(1, res.getData()[2][1], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(1, res.getData()[2][2], Mathf.FLOAT_ERROR);

        m1 = new Matrix3x3();
        m1.getData()[0][0] = 1;
        m1.getData()[0][1] = 2;
        m1.getData()[1][1] = 1;
        m1.getData()[1][2] = 3;
        m1.getData()[2][2] = 4;

        m2 = new Matrix3x3();
        m2.getData()[0][0] = 2;
        m2.getData()[0][2] = 1;
        m2.getData()[1][1] = 5;
        m2.getData()[2][0] = 3;
        m2.getData()[2][1] = 2;

        res = m1.mul(m2);
        System.out.println(res.toString());
        Assertions.assertEquals(2, res.getData()[0][0], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(10, res.getData()[0][1], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(1, res.getData()[0][2], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(9, res.getData()[1][0], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(11, res.getData()[1][1], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, res.getData()[1][2], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(12, res.getData()[2][0], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(8, res.getData()[2][1], Mathf.FLOAT_ERROR);
        Assertions.assertEquals(0, res.getData()[2][2], Mathf.FLOAT_ERROR);
    }
}
