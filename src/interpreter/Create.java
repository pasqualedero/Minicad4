package interpreter;

import command.Command;
import command.specificcommand.CreateCmd;
import interpreter.typeConstr.TypeConstr;
import shapes.view.GraphicObjectPanel;

public class Create implements CombinazioneTerminale{
    private TypeConstr typeConstr;
    private Pos pos;

    public Create(TypeConstr typeConstr, Pos pos) {
        this.typeConstr = typeConstr;
        this.pos = pos;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia tc = typeConstr.interpreta(null,input);
        CombinazioneIntermedia posizione = pos.interpreta(null,input);
        return new CreateCmd((TypeConstr) tc, (Pos) posizione, contesto);
    }
}
