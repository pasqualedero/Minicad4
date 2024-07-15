package interpreter;

import command.Command;

public class PosFloat implements CombinazioneIntermedia{
    private float pos;

    public PosFloat(float pos) {
        if (pos<0)
            throw new RuntimeException("pos è <0");
        this.pos = pos;
    }

    public float getPos() {
        return pos;
    }

    @Override
    public CombinazioneIntermedia interpreta(Command contesto, String input) {
        //contesto.setTesto(" posfloat:"+pos);
        return this;
    }
}
