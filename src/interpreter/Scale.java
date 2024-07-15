package interpreter;

import command.Command;
import command.specificcommand.ScaleCmd;
import shapes.view.GraphicObjectPanel;

public class Scale implements CombinazioneTerminale{

    private ObjID objID;
    private PosFloat posFloat;

    public Scale(ObjID objID, PosFloat posFloat) {
        this.objID = objID;
        this.posFloat = posFloat;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia id = objID.interpreta(null,input);
        CombinazioneIntermedia posfloat = posFloat.interpreta(null,input);
        return new ScaleCmd((ObjID) id, (PosFloat) posfloat,contesto);
    }
}
