package interpreter.list;

import command.Command;
import command.specificcommand.listCmd.ListAllGroupsCmd;
import shapes.view.GraphicObjectPanel;

public class ListGroups extends List{
    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        //contesto.setTesto(" keyword:groups");
        return new ListAllGroupsCmd(false);
    }
}
