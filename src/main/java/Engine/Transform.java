package Engine;

import Engine.Math.Vector3;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Transform extends Component {
    public static class HasSuchTransformChildException extends RuntimeException {
        HasSuchTransformChildException(Transform transform) {
            super("Has such transform as child: " + transform.gameObject.toString());
        }
    }

    @Setter private Vector3 localPosition, localRotation, localScale;
    private List<Transform> children = new ArrayList<>();
    private Transform parent;

    public Transform() {
        localPosition = new Vector3();
        localRotation = new Vector3();
        localScale = new Vector3();
    }

    public void addChild(Transform transform) {
        if(children.contains(transform)) {
            throw new HasSuchTransformChildException(transform);
        }

        children.add(transform);
        transform.parent = this;
    }

    public Vector3 getGlobalPosition() {
        var position = localPosition.clone();
        var par = getParent();
        while(par != null) {
            position = Vector3.add(position, par.localPosition);
            par = par.parent;
        }

        return position;
    }

    public Vector3 getGlobalRotation() {
        var rotation = localRotation.clone();
        var par = getParent();
        while(par != null) {
            rotation = Vector3.add(rotation, par.localRotation);
            par = par.parent;
        }

        return rotation;
    }

    public Vector3 getGlobalScale() {
        var scale = localScale.clone();
        var par = getParent();
        while(par != null) {
            scale = Vector3.mul(scale, par.localScale);
            par = par.parent;
        }

        return scale;
    }
}
