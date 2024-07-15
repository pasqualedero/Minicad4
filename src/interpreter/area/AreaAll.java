package interpreter.area;

import command.Command;
import command.specificcommand.areaCmd.AreaAllCmd;
import shapes.view.GraphicObjectPanel;

public class AreaAll extends Area{

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        //contesto.setTesto(" keyword:all");
        return new AreaAllCmd(contesto);
    }
}
