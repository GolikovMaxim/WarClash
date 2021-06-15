package Engine;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GameObject implements ComponentBehaviour {
    private static class HasSuchComponentException extends RuntimeException {
        HasSuchComponentException(Class componentType) {
            super(componentType.getName());
        }
    }

    Map<Class, List<Component>> components = new HashMap<>();
    private List<Component> componentList = new ArrayList<>();
    @Getter @Setter private String name;
    @Getter private Transform transform;
    @Getter @Setter(AccessLevel.PACKAGE) private Scene scene;

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
            var list = components.computeIfAbsent(type, c -> new ArrayList<>());
            list.add(component);
            type = type.getSuperclass();
        }

        componentList.add(component);
        component.start();

        return component;
    }

    public <T extends Component> T getComponent(Class<T> componentType) {
        if(components.get(componentType) != null) {
            return (T)components.get(componentType).get(0);
        }
        return null;
    }

    public <T extends Component> List<T> getComponents(Class<T> componentType) {
        return (List<T>)components.get(componentType);
    }

    public <T extends Component> void removeComponent(Class<T> componentType) {
        var component = getComponent(componentType);
        if(component == null) {
            return;
        }

        Class type = componentType;
        while(!type.equals(Component.class) && getComponent(type) == component) {
            components.get(type).remove(component);
            type = type.getSuperclass();
        }

        componentList.remove(component);
        component.onDestroy();
    }

    @Override
    public void start() {
        componentList.forEach(component -> component.start());
    }

    @Override
    public void update() {
        componentList.forEach(component -> component.update());
    }

    @Override
    public void onDestroy() {
        componentList.forEach(component -> component.onDestroy());

        componentList.clear();
        components.clear();
    }

    @Override
    public String toString() {
        return "GameObject: " + name;
    }
}
