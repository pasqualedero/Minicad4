package interpreter;

import command.Command;

public class Type implements CombinazioneIntermedia{
    private Simboli simbolo;

    public Type(Simboli figura) {
        this.simbolo = figura;
    }

    public Simboli getSimbolo() {
        return simbolo;
    }

    @Override
    public CombinazioneIntermedia interpreta(Command contesto, String input) {
        //contesto.setTesto(" type:"+simbolo);
        return this;
    }
}
