package Engine.Physics;

import Engine.Math.Vector3;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CircleTrigger extends Trigger {
    private float radius;

    @Override
    public boolean checkCollision(Trigger trigger) {
        if(trigger instanceof CircleTrigger) {
            return Vector3.distance(getGameObject().getTransform().getGlobalPosition(),
                    trigger.getGameObject().getTransform().getGlobalPosition())
                    <= radius + ((CircleTrigger) trigger).radius;
        }

        return false;
    }
}
