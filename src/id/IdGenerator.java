package id;

import exception.InvalidID;
import shapes.model.GraphicObject;

import java.util.HashMap;
import java.util.Set;

public enum IdGenerator { // Singleton
    ISTANCE;

    private static int COUNTER=0;
    private HashMap<Integer, GraphicObject> list = new HashMap<>();

    public int add(GraphicObject go){
        list.put(++COUNTER, go);
        return COUNTER;
    }

    public GraphicObject get(int id){
        GraphicObject res = list.get(id);
        if (res==null)
            throw new InvalidID("ID fornito non corrisponde ad alcun elemento");
        return res;
    }

    public void delete(int id){
        if (!list.containsKey(id))
            throw new InvalidID("ID fornito non corrisponde ad alcun elemento");
        list.remove(id);
    }

    public Set<Integer> getKeySet(){
        return list.keySet();
    }

    public void clear(){ //for testing purposes onlyyy
        list.clear();
    }

}
