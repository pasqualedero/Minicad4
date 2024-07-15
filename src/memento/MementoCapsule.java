package memento;

import shapes.model.GraphicObject;

public class MementoCapsule {
    private GraphicObject go;
    private Memento memento;

    public MementoCapsule(GraphicObject go, Memento memento) {
        this.go = go;
        this.memento = memento;
    }

    public GraphicObject getGo() {
        return go;
    }

    public Memento getMemento() {
        return memento;
    }
}
