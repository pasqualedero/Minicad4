package command.specificcommand.perimeterCmd;

import command.Command;
import command.DeclarativeCommand;
import id.IdGenerator;
import interpreter.ObjID;
import shapes.model.GraphicObject;
import shapes.view.GraphicObjectPanel;

import javax.swing.*;

public class PerimeterIdCmd implements Command , DeclarativeCommand {
    private ObjID id;
    private GraphicObjectPanel gpanel;
    private GraphicObject go;

    public PerimeterIdCmd(ObjID id, GraphicObjectPanel gpanel) {
        this.id = id;
        this.gpanel = gpanel;
        go = IdGenerator.ISTANCE.get(id.getId());
    }

    @Override
    public boolean doIt() {
        String resFin = String.format("%.2f",go.perimetro());
        System.out.println("Perimetro di "+id+": "+resFin);

        JOptionPane.showMessageDialog(gpanel, "Perimetro di "+go +": "+resFin,
                "Calcolo Perimetro", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));

        return false;
    }



    @Override
    public GraphicObject getGraphicObject() {
        return go;
    }
}
