package interpreter.perimeter;

import command.Command;
import command.specificcommand.perimeterCmd.PerimeterIdCmd;
import interpreter.CombinazioneIntermedia;
import interpreter.ObjID;
import shapes.view.GraphicObjectPanel;

public class PerimeterObjID extends Perimeter{
    private ObjID id;

    public PerimeterObjID(ObjID id) {
        this.id = id;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia idObj = id.interpreta(null,input);
        return new PerimeterIdCmd((ObjID) idObj, contesto);
    }
}
