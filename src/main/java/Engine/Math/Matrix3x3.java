package Engine.Math;

import lombok.Getter;

@Getter
public class Matrix3x3 {
    public enum RotationAxis {
        AXIS_X, AXIS_Y, AXIS_Z
    }

    public static Matrix3x3 one = new Matrix3x3();
    static {
        one.data[0][0] = 1;
        one.data[1][1] = 1;
        one.data[2][2] = 1;
    }

    private float[][] data;

    public Matrix3x3() {
        data = new float[3][3];
    }

    public Matrix3x3(Vector3 axis, float angle) {
        this();

        axis = axis.normalized();
        var sin = Mathf.sin(angle);
        var cos = Mathf.cos(angle);
        var x = axis.getX();
        var y = axis.getY();
        var z = axis.getZ();

        data[0][0] = cos + (1 - cos) * x * x;
        data[0][1] = (1 - cos) * x * y - sin * z;
        data[0][2] = (1 - cos) * x * z + sin * y;

        data[1][0] = (1 - cos) * y * x + sin * z;
        data[1][1] = cos + (1 - cos) * y * y;
        data[1][2] = (1 - cos) * y * x - sin * x;

        data[2][0] = (1 - cos) * z * x - sin * y;
        data[2][1] = (1 - cos) * z * y + sin * x;
        data[2][2] = cos + (1 - cos) * z * z;
    }

    public Matrix3x3(RotationAxis axis, float angle) {
        this();

        var sin = Mathf.sin(angle);
        var cos = Mathf.cos(angle);

        switch (axis) {
            case AXIS_X:
                data[0][0] = 1;

                data[1][1] = cos;
                data[1][2] = -sin;

                data[2][1] = sin;
                data[2][2] = cos;
                break;
            case AXIS_Y:
                data[0][0] = cos;
                data[0][2] = sin;

                data[1][1] = 1;

                data[2][0] = -sin;
                data[2][2] = cos;
                break;
            case AXIS_Z:
                data[0][0] = cos;
                data[0][1] = -sin;

                data[1][0] = sin;
                data[1][1] = cos;

                data[2][2] = 1;
                break;
        }
    }

    public Matrix3x3(Vector3 eulerAngles) {
        data = new Matrix3x3(RotationAxis.AXIS_X, eulerAngles.getX()).
                mul(new Matrix3x3(RotationAxis.AXIS_Y, eulerAngles.getY())).
                mul(new Matrix3x3(RotationAxis.AXIS_Z, eulerAngles.getZ())).getData();

        /*data = new Matrix3x3(Vector3.right, eulerAngles.getX()).
                mul(new Matrix3x3(Vector3.up, eulerAngles.getY())).
                mul(new Matrix3x3(Vector3.forward, eulerAngles.getZ())).getData();*/
    }

    public static Matrix3x3 add(Matrix3x3 a, Matrix3x3 b) {
        var res = new Matrix3x3();

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                res.data[i][j] = a.data[i][j] + b.data[i][j];
            }
        }

        return res;
    }

    public static Matrix3x3 sub(Matrix3x3 a, Matrix3x3 b) {
        var res = new Matrix3x3();

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                res.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }

        return res;
    }

    public static Matrix3x3 mul(Matrix3x3 a, Matrix3x3 b) {
        var res = new Matrix3x3();

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                float value = 0;

                for(int k = 0; k < 3; k++) {
                    value += a.data[i][k] * b.data[k][j];
                }

                res.data[i][j] = value;
            }
        }

        return res;
    }

    public static Vector3 mul(Matrix3x3 a, Vector3 b) {
        var res = new Vector3();

        res.setX(a.data[0][0] * b.getX() + a.data[0][1] * b.getY() + a.data[0][2] * b.getZ());
        res.setY(a.data[1][0] * b.getX() + a.data[1][1] * b.getY() + a.data[1][2] * b.getZ());
        res.setZ(a.data[2][0] * b.getX() + a.data[2][1] * b.getY() + a.data[2][2] * b.getZ());

        return res;
    }

    public static Matrix3x3 transpose(Matrix3x3 a) {
        var res = new Matrix3x3();

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                res.data[i][j] = a.data[j][i];
            }
        }

        return res;
    }

    public Matrix3x3 add(Matrix3x3 a) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                data[i][j] += a.data[i][j];
            }
        }
        return this;
    }

    public Matrix3x3 sub(Matrix3x3 a) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                data[i][j] -= a.data[i][j];
            }
        }
        return this;
    }

    public Matrix3x3 mul(Matrix3x3 a) {
        return mul(this, a);
    }

    public Vector3 mul(Vector3 a) {
        return mul(this, a);
    }

    public Matrix3x3 transposed() {
        return transpose(this);
    }

    @Override
    public String toString() {
        return "Matrix 3x3: \n" +
                data[0][0] + " " + data[0][1] + " " + data[0][2] + "\n" +
                data[1][0] + " " + data[1][1] + " " + data[1][2] + "\n" +
                data[2][0] + " " + data[2][1] + " " + data[2][2];
    }
}
