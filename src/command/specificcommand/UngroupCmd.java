package command.specificcommand;

import command.Command;
import id.IdGenerator;
import interpreter.ObjID;
import shapes.model.CompositeObject;
import shapes.model.GraphicObject;
import shapes.view.GraphicObjectPanel;

import javax.swing.*;

public class UngroupCmd implements Command {
    private ObjID id;
    private GraphicObjectPanel gpanel;
    private GraphicObject go;

    public UngroupCmd(ObjID id, GraphicObjectPanel gpanel) {
        this.id = id;
        this.gpanel=gpanel;
        go = IdGenerator.ISTANCE.get(id.getId());
    }

    @Override
    public boolean doIt() {
        if (go instanceof CompositeObject){
            gpanel.remove(go);
            IdGenerator.ISTANCE.delete(id.getId());

            JOptionPane.showMessageDialog(gpanel, "Hai sciolto il gruppo con ID: "+id.getId(),
                    "Gruppo Sciolto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));

        }
        return false;
    }

    @Override
    public GraphicObject getGraphicObject() {
        return go;
    }
}
