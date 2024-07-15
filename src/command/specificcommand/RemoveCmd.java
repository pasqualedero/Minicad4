package command.specificcommand;

import command.Command;
import exception.InvalidID;
import id.IdGenerator;
import interpreter.ObjID;
import shapes.model.CompositeObject;
import shapes.model.GraphicObject;
import shapes.view.GraphicObjectPanel;

import javax.swing.*;
import java.util.LinkedList;

public class RemoveCmd implements Command {
    private ObjID id;
    private GraphicObjectPanel gpanel;
    private GraphicObject go;

    public RemoveCmd(ObjID id, GraphicObjectPanel gpanel) {
        this.id = id;
        this.gpanel = gpanel;
        this.go = IdGenerator.ISTANCE.get(id.getId());
    }

    @Override
    public boolean doIt() {
        if (go instanceof CompositeObject){
            LinkedList<Integer> children = ((CompositeObject) go).getChildren();
            for (Integer i : children){
                try{
                    GraphicObject graphicObject = IdGenerator.ISTANCE.get(i);
                    gpanel.remove(graphicObject); // TODO magari mettilo in un metodo
                    IdGenerator.ISTANCE.delete(i);
                }catch (InvalidID e){}
            }
        }

        gpanel.remove(go);
        IdGenerator.ISTANCE.delete(id.getId());

        JOptionPane.showMessageDialog(gpanel, "Rimozione di:\n"+go.toString()+".\n",
                "Rimozione avvenuta con successo!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));

        return false;
    }


    @Override
    public GraphicObject getGraphicObject() {
        return this.go;
    }
}
