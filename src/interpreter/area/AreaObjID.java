package interpreter.area;

import command.Command;
import command.specificcommand.areaCmd.AreaIdCmd;
import interpreter.CombinazioneIntermedia;
import interpreter.ObjID;
import shapes.view.GraphicObjectPanel;

public class AreaObjID extends Area{
    private ObjID id;

    public AreaObjID(ObjID id) {
        this.id = id;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia idObj = id.interpreta(null,input);
        return new AreaIdCmd((ObjID) idObj,contesto);
    }
}
