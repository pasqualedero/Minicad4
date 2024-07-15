package interpreter.move;

import command.Command;
import command.specificcommand.MoveCmd;
import interpreter.CombinazioneIntermedia;
import interpreter.ObjID;
import interpreter.Pos;
import shapes.view.GraphicObjectPanel;

public class MvOff extends Move{

    public MvOff(ObjID id, Pos pos) {
        this.id = id;
        this.pos = pos;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        //contesto.setTesto(" keyword: mvOff");
        CombinazioneIntermedia objID = id.interpreta(null,input);
        CombinazioneIntermedia posizione = pos.interpreta(null,input);
        return new MoveCmd(true, (ObjID) objID, (Pos) posizione, contesto);
    }
}
