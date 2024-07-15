package interpreter.list;

import command.Command;
import command.specificcommand.listCmd.ListTypeCmd;
import interpreter.CombinazioneIntermedia;
import interpreter.Type;
import shapes.view.GraphicObjectPanel;

public class ListType extends List{
    private Type type;

    public ListType(Type type) {
        this.type = type;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia tipo = type.interpreta(null,input);
        return new ListTypeCmd((Type) tipo);
    }
}
