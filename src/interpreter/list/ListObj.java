package interpreter.list;

import command.Command;
import command.specificcommand.listCmd.ListIdCmd;
import interpreter.CombinazioneIntermedia;
import interpreter.ObjID;
import shapes.view.GraphicObjectPanel;

public class ListObj extends List{
    private ObjID id;

    public ListObj(ObjID id) {
        this.id = id;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia idObj = id.interpreta(null,input);
        return new ListIdCmd((ObjID) idObj,contesto);
    }
}
