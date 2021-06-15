package Engine.Test;

import Engine.Engine;
import Engine.GameObject;
import Engine.Math.Mathf;
import Engine.Math.Vector3;
import Engine.Physics.RigidBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RigidBodyTest {
    @Test
    public void testRigidBodyVelocity() {
        var go = new GameObject("rb_test");
        var tf = go.getTransform();
        var rb = go.addComponent(RigidBody.class);

        rb.setVelocity(new Vector3(0, 0, 1));
        rb.update();
        Assertions.assertEquals(1f / Engine.FIXED_DELTA_TIME, tf.getGlobalPosition().getZ(), Mathf.FLOAT_ERROR);

        tf.setGlobalPosition(Vector3.zero);

        tf.setLocalRotation(new Vector3(0, 90 * Mathf.DEG2RAD, 0));
        rb.setVelocity(new Vector3(0, 0, 1));
        rb.update();
        var globalPosition = tf.getGlobalPosition();
        System.out.println(globalPosition.toString());
        Assertions.assertEquals(1f / Engine.FIXED_DELTA_TIME, go.getTransform().getGlobalPosition().getZ(), Mathf.FLOAT_ERROR);

        tf.setGlobalPosition(Vector3.zero);

        tf.setLocalRotation(new Vector3(0, 0, 90 * Mathf.DEG2RAD));
        rb.setRelativeVelocity(new Vector3(0, 0, 1));
        rb.update();
        globalPosition = tf.getGlobalPosition();
        System.out.println(globalPosition.toString());
        Assertions.assertEquals(1f / Engine.FIXED_DELTA_TIME, go.getTransform().getGlobalPosition().getZ(), Mathf.FLOAT_ERROR);

        tf.setGlobalPosition(Vector3.zero);

        tf.setLocalRotation(new Vector3(90 * Mathf.DEG2RAD, 90 * Mathf.DEG2RAD, 0));
        rb.setRelativeVelocity(new Vector3(1, 0, 1));
        rb.update();
        globalPosition = tf.getGlobalPosition();
        System.out.println(globalPosition.toString());
        Assertions.assertEquals(-1f / Engine.FIXED_DELTA_TIME, go.getTransform().getGlobalPosition().getY(), Mathf.FLOAT_ERROR);
        Assertions.assertEquals(-1f / Engine.FIXED_DELTA_TIME, go.getTransform().getGlobalPosition().getZ(), Mathf.FLOAT_ERROR);
    }
}
