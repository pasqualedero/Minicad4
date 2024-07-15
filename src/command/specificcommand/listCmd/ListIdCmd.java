package command.specificcommand.listCmd;

import command.Command;
import command.DeclarativeCommand;
import id.IdGenerator;
import interpreter.ObjID;
import shapes.model.GraphicObject;
import shapes.view.GraphicObjectPanel;

import javax.swing.*;

public class ListIdCmd implements Command , DeclarativeCommand {
    private GraphicObject go;
    private GraphicObjectPanel gpanel;

    public ListIdCmd(ObjID id, GraphicObjectPanel gpanel) {
        this.go = IdGenerator.ISTANCE.get(id.getId());
        this.gpanel = gpanel;
    }

    @Override
    public boolean doIt() {
        System.out.println(go.toString());
        JOptionPane.showMessageDialog(gpanel,go.toString(),
                "Operazione avvenuta con successo!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));

        return false;
    }


    @Override
    public GraphicObject getGraphicObject() {
        return null;
    }
}
