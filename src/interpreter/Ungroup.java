package interpreter;

import command.Command;
import command.specificcommand.UngroupCmd;
import shapes.view.GraphicObjectPanel;

public class Ungroup implements CombinazioneTerminale{
    private ObjID idGruppo;

    public Ungroup(ObjID idGruppo) {
        this.idGruppo = idGruppo;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia id = idGruppo.interpreta(null,input);
        return new UngroupCmd((ObjID) id, contesto);
    }
}
