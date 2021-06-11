package Engine;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class GameObject {
    public static class HasSuchComponentException extends RuntimeException {
        HasSuchComponentException(Class componentType) {
            super(componentType.getName());
        }
    }

    Map<Class, Component> components = new HashMap<>();
    @Getter @Setter private String name;
    @Getter private Transform transform;

    public GameObject(String name) {
        this.name = name;
        transform = addComponent(Transform.class);
    }

    @SneakyThrows
    public <T extends Component> T addComponent(Class<T> componentType) {
        if(getComponent(componentType) != null) {
            throw new HasSuchComponentException(componentType);
        }

        var component = componentType.getDeclaredConstructor().newInstance();
        component.gameObject = this;

        Class type = componentType;
        while(!type.equals(Component.class)) {
            components.put(type, component);
            type = type.getSuperclass();
        }

        return component;
    }

    public <T extends Component> T getComponent(Class<T> componentType) {
        return (T)components.get(componentType);
    }

    public <T extends Component> void removeComponent(Class<T> componentType) {
        Class type = componentType;
        while(!type.equals(Component.class)) {
            components.remove(type);
            type = type.getSuperclass();
        }
    }

    @Override
    public String toString() {
        return "GameObject: " + name;
    }
}
