package Engine;

import Engine.Math.Matrix3x3;
import Engine.Math.Vector3;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Transform extends Component {
    public static class HasSuchTransformChildException extends RuntimeException {
        HasSuchTransformChildException(Transform parent, Transform child) {
            super("Transform " + parent.gameObject.toString() + " has such transform as child: " + child.gameObject.toString());
        }
    }

    @Getter private Vector3 localPosition, localRotation;
    @Getter private Transform parent;

    private List<Transform> children = new ArrayList<>();
    private Matrix3x3 rotationMatrix;

    public Transform() {
        setLocalPosition(new Vector3());
        setLocalRotation(new Vector3());
    }

    public void addChild(Transform transform) {
        if(children.contains(transform)) {
            throw new HasSuchTransformChildException(this, transform);
        }

        children.add(transform);
        transform.parent = this;
    }

    public void removeChild(Transform transform) {
        if(!children.contains(transform)) {
            return;
        }

        children.remove(transform);
        transform.parent = null;
    }

    public Transform[] getChildren() {
        return children.toArray(new Transform[0]);
    }

    public void setParent(Transform transform) {
        transform.addChild(this);
    }

    public void setLocalPosition(Vector3 p) {
        localPosition = p;
    }

    public void setLocalRotation(Vector3 r) {
        localRotation = r;
        rotationMatrix = new Matrix3x3(r);
    }

    public Vector3 getGlobalPosition() {
        var position = localPosition.clone();
        var par = getParent();
        while(par != null) {
            position = Vector3.add(par.rotationMatrix.mul(position), par.localPosition);
            par = par.parent;
        }

        return position;
    }

    public Matrix3x3 getGlobalRotationMatrix() {
        var rotation = rotationMatrix;
        var par = getParent();
        while(par != null) {
            rotation = par.rotationMatrix.mul(rotation);
            par = par.parent;
        }

        return rotation;
    }

    public void setGlobalPosition(Vector3 position) {
        if(parent == null) {
            setLocalPosition(position);
            return;
        }

        var parentRotation = parent.getGlobalRotationMatrix().transposed();
        var globalPosition = getGlobalPosition();
        var delta = parentRotation.mul(Vector3.sub(position, globalPosition));
        setLocalPosition(Vector3.add(localPosition, delta));
    }
}
