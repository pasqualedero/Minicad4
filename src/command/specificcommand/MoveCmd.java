package command.specificcommand;

import command.Command;
import id.IdGenerator;
import interpreter.ObjID;
import interpreter.Pos;
import shapes.model.GraphicObject;
import shapes.view.GraphicObjectPanel;

import javax.swing.*;

public class MoveCmd implements Command {
    private boolean mvOff; //se false -> mv
    private ObjID id;
    private Pos pos;
    private GraphicObjectPanel gpanel;
    private GraphicObject go;

    public MoveCmd(boolean mvOff, ObjID id, Pos pos, GraphicObjectPanel gpanel) {
        this.mvOff = mvOff;
        this.id = id;
        this.pos = pos;
        this.gpanel = gpanel;
        go = IdGenerator.ISTANCE.get(id.getId());
    }

    @Override
    public boolean doIt() {
        double currentX = 0;
        double currentY = 0;

        if (mvOff){
            currentX = go.getPosition().getX();
            currentY = go.getPosition().getY();
        }

        go.moveTo(pos.getX().getPos()+currentX,pos.getY().getPos()+currentY);

        JOptionPane.showMessageDialog(gpanel, "Nuova posizione:\n"+go.toString()+".\n",
                "Operazione avvenuta con successo!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));

        return true;
    }


    @Override
    public GraphicObject getGraphicObject() {
        return go;
    }
}
