package interpreter;

import command.Command;

import java.util.LinkedList;

public class ListID implements CombinazioneIntermedia{
    private LinkedList<ObjID> listaID = new LinkedList<>();

    public ListID(ObjID id) {
        listaID.add(id);
    }

    public void addObjID(ObjID id){
        listaID.add(id);
    }

    public LinkedList<ObjID> getListaID() {
        return listaID;
    }

    @Override
    public CombinazioneIntermedia interpreta(Command contesto, String input) {
        for(ObjID id : listaID){
            id.interpreta(contesto,input);
        }
        return this;
    }
}
