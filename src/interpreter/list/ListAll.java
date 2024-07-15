package interpreter.list;

import command.Command;
import command.specificcommand.listCmd.ListAllGroupsCmd;
import shapes.view.GraphicObjectPanel;

public class ListAll extends List{

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        //contesto.setTesto(" keyword: all");
        return new ListAllGroupsCmd(true);
    }
    // costruttore di default

}
