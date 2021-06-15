package Engine.Physics;

import Engine.Component;

abstract class Trigger extends Component {
    public abstract boolean checkCollision(Trigger trigger);
}
