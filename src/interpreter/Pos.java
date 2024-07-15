package interpreter;

import command.Command;

public class Pos implements CombinazioneIntermedia{
    private PosFloat x,y;

    public Pos(PosFloat x, PosFloat y) {
        this.x = x;
        this.y = y;
    }

    public PosFloat getX() {
        return x;
    }

    public PosFloat getY() {
        return y;
    }

    @Override
    public CombinazioneIntermedia interpreta(Command contesto, String input) {
        x.interpreta(contesto,input);
        y.interpreta(contesto,input);
        return this;
    }
}
