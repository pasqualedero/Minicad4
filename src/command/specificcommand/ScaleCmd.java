package command.specificcommand;

import command.Command;
import id.IdGenerator;
import interpreter.ObjID;
import interpreter.PosFloat;
import shapes.model.GraphicObject;
import shapes.view.GraphicObjectPanel;

import javax.swing.*;

public class ScaleCmd implements Command {

    private PosFloat factor;
    private GraphicObjectPanel gpanel;
    private GraphicObject go;

    public ScaleCmd(ObjID id, PosFloat factor, GraphicObjectPanel gpanel) {
        this.factor = factor;
        this.gpanel = gpanel;
        go = IdGenerator.ISTANCE.get(id.getId());
    }

    @Override
    public boolean doIt() {
        go.scale(factor.getPos());

        JOptionPane.showMessageDialog(gpanel, "Nuova dimensione:\n"+go.toString()+".\n",
                "Operazione avvenuta con successo!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));

        return true;
    }


    @Override
    public GraphicObject getGraphicObject() {
        return go;
    }
}
