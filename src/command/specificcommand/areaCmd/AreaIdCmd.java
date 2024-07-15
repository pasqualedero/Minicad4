package command.specificcommand.areaCmd;

import command.Command;
import command.DeclarativeCommand;
import id.IdGenerator;
import interpreter.ObjID;
import shapes.model.GraphicObject;
import shapes.view.GraphicObjectPanel;

import javax.swing.*;

public class AreaIdCmd implements Command , DeclarativeCommand {
    private GraphicObjectPanel  gpanel;
    private GraphicObject go;

    public AreaIdCmd(ObjID id, GraphicObjectPanel gpanel) {
        this.gpanel = gpanel;
        go = IdGenerator.ISTANCE.get(id.getId());
    }

    @Override
    public boolean doIt() {
        String resFin = String.format("%.2f",go.area());
        System.out.println("Area di "+go +": "+resFin);

        JOptionPane.showMessageDialog(gpanel, "Area di "+go +": "+resFin,
                "Calcolo Area", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));

        return false;
    }


    @Override
    public GraphicObject getGraphicObject() {
        return null;
    }
}
