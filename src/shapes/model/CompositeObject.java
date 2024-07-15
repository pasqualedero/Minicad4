package shapes.model;

import exception.InvalidID;
import id.IdGenerator;
import interpreter.typeConstr.TypeConstr;
import memento.Memento;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedList;

public class CompositeObject extends AbstractGraphicObject{

    private LinkedList<Integer> children;

    public CompositeObject(LinkedList<Integer> children) {
        this.children = children;
    }

    @Override
    public Memento getMemento() {
        HashMap<GraphicObject,Memento> mappa = new HashMap<>();
        for (Integer i : children){
            try{
                GraphicObject go = IdGenerator.ISTANCE.get(i);
                mappa.put(go, go.getMemento());
            }catch (InvalidID e){}
        }
        return new CompositeMemento(mappa);
    }

    @Override
    public void setMemento(Memento m) {
        if (m instanceof  CompositeMemento cm){
            for (GraphicObject go : cm.mementoMap().keySet()){
                go.setMemento(cm.mementoMap().get(go));
            }
        }
    }

    private record CompositeMemento(HashMap<GraphicObject,Memento> mementoMap) implements Memento{

    }

    @Override
    public void moveTo(Point2D p) {
        Point2D position = getPosition();
        for (Integer id : children){
            try {
                GraphicObject go = IdGenerator.ISTANCE.get(id);
                go.moveTo(new Point2D.Double(go.getPosition().getX()+p.getX()-position.getX(),go.getPosition().getY()+p.getY()-position.getY()));
            } catch (InvalidID e){}
        }
    }

    @Override
    public Point2D getPosition() {
        /*
        NB: per definire la posizione del Composite, si sceglie di utilizzare il centroide come punto di riferimento;
        questo è ri-calcolato ogni qualvolta il il metodo è chiamato, poichè l'eventuale rimozione di un
        GraphicObject dal gruppo comporterebbe la variazione del centroide stesso.
         */
        double sommaAscisse=0, sommaOrdinate=0;
        int numElem = 0;

        for (Integer i : children){
            try{
                GraphicObject graphicObject = IdGenerator.ISTANCE.get(i);
                numElem++;
                sommaAscisse += graphicObject.getPosition().getX();
                sommaOrdinate += graphicObject.getPosition().getY();
            }catch (InvalidID e){}
        }
        return new Point2D.Double(sommaAscisse/numElem,sommaOrdinate/numElem);
    }

    @Override
    public void setDimension(TypeConstr t) {

    }

    @Override
    public Dimension2D getDimension() { // TODO: not defined
        return null;
    }

    @Override
    public void scale(double factor) {
        for (Integer id : children){
            try {
                GraphicObject go = IdGenerator.ISTANCE.get(id);
                go.scale(factor);
            }catch (InvalidID e){}
        }
    }

    @Override
    public boolean contains(Point2D p) {
        for (Integer id : children){
            try {
                GraphicObject go = IdGenerator.ISTANCE.get(id);
                if (go.contains(p))
                    return true;
            } catch (InvalidID e){}
        }
        return false;
    }

    @Override
    public double area() {
        double res=0;
        for (Integer i : children){
            try{
                GraphicObject go = IdGenerator.ISTANCE.get(i);
                res += go.area();
            }catch (InvalidID e){}
        }
        return res;
    }

    @Override
    public double perimetro() {
        double res=0;
        for (Integer i : children){
            try{
                GraphicObject go = IdGenerator.ISTANCE.get(i);
                res += go.perimetro();
            }catch (InvalidID e){}
        }
        return res;
    }

    @Override
    public CompositeObject clone() {
        CompositeObject cloned = (CompositeObject) super.clone();
        cloned.children = new LinkedList<>();
        for (Integer id : children){
            cloned.children.add(id);
        }
        return cloned;
    }

    public LinkedList<Integer> getChildren(){ //TODO pensa di fare un'interfaccia
        return children;
    }

    @Override
    public String getType() {
       String res = "Composite: ";
       for (Integer i : children){
           try {
               GraphicObject go = IdGenerator.ISTANCE.get(i);
               res += go.getType();
               res += " ";
           } catch (InvalidID e){}
       }
       return res;
    }

    @Override
    public String toString() {
        StringBuilder res= new StringBuilder("GRUPPO\n");
        for (Integer i : children){
            try{
                GraphicObject go = IdGenerator.ISTANCE.get(i);
                res.append(go).append("\n");
            }catch (InvalidID e){}
        }
        res.append("(fine gruppo)");
        return res.toString();
    }
}
