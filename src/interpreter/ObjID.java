package interpreter;

import command.Command;

public class ObjID implements CombinazioneIntermedia{
    private int id;

    public ObjID(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    @Override
    public CombinazioneIntermedia interpreta(Command contesto, String input) {
        return this;
    }

}
