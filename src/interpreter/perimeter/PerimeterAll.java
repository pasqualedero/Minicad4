package interpreter.perimeter;

import command.Command;
import command.specificcommand.perimeterCmd.PerimeterAllCmd;
import shapes.view.GraphicObjectPanel;

public class PerimeterAll extends Perimeter{
    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        //contesto.setTesto("keyword:all");
        return new PerimeterAllCmd(contesto);
    }
}
