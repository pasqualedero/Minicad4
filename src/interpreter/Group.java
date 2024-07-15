package interpreter;

import command.Command;
import command.specificcommand.GroupCmd;
import shapes.view.GraphicObjectPanel;

public class Group implements CombinazioneTerminale{
    private ListID lista;

    public Group(ListID lista) {
        this.lista = lista;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia listaID = lista.interpreta(null,input);
        return new GroupCmd((ListID) listaID, contesto);
    }
}
