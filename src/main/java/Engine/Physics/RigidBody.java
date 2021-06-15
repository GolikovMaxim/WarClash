package Engine.Physics;

import Engine.Component;
import Engine.Engine;
import Engine.Events.GenericEvent;
import Engine.Math.Vector3;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public final class RigidBody extends Component {
    private static final List<RigidBody> rigidBodies = new ArrayList<>();

    @Getter private GenericEvent<RigidBody>
            onTriggerEnter = new GenericEvent<>(),
            onTriggerStay = new GenericEvent<>(),
            onTriggerExit = new GenericEvent<>();

    private List<RigidBody> triggeredWith = new ArrayList<>();
    @Getter @Setter private Vector3 velocity = new Vector3();

    @Override
    public void start() {
        rigidBodies.add(this);
    }

    @Override
    public void update() {
        getGameObject().getTransform().setGlobalPosition(
                getGameObject().getTransform().getGlobalPosition().
                        add(velocity.mul(1f / Engine.FIXED_DELTA_TIME)));

        var trigger = getGameObject().getComponent(Trigger.class);

        for(var rb : rigidBodies) {
            var rbTrigger = rb.getGameObject().getComponent(Trigger.class);

            if(trigger != null && rbTrigger != null) {
                if(trigger.checkCollision(rbTrigger)) {
                    if(!triggeredWith.contains(rb)) {
                        triggeredWith.add(rb);
                        onTriggerEnter.invoke(rb);
                    } else {
                        onTriggerStay.invoke(rb);
                    }
                } else {
                    if(triggeredWith.contains(rb)) {
                        triggeredWith.remove(rb);
                        onTriggerExit.invoke(rb);
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        rigidBodies.remove(this);
    }

    public void setRelativeVelocity(Vector3 vel) {
        velocity = getGameObject().getTransform().getGlobalRotationMatrix().mul(vel);
    }
}
