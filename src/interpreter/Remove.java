package interpreter;

import command.Command;
import command.specificcommand.RemoveCmd;
import shapes.view.GraphicObjectPanel;

public class Remove implements CombinazioneTerminale{
    private ObjID id;

    public Remove(ObjID id) {
        this.id = id;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia idObj = id.interpreta(null,input);
        return new RemoveCmd((ObjID) idObj, contesto);
    }
}
