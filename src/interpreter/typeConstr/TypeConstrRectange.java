package interpreter.typeConstr;

import command.Command;
import interpreter.CombinazioneIntermedia;
import interpreter.PosFloat;
import interpreter.Simboli;
import interpreter.Type;

public class TypeConstrRectange extends TypeConstr{
    private PosFloat base, altezza;
    private Type tipo=new Type(Simboli.RECTANGLE);

    public TypeConstrRectange(PosFloat base, PosFloat altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public CombinazioneIntermedia interpreta(Command contesto, String input) {
        tipo.interpreta(contesto,input);
        base.interpreta(contesto,input);
        altezza.interpreta(contesto,input);
        return this;
    }

    @Override
    public Type getTipo() {
        return tipo;
    }

    public float getBase() {
        return base.getPos();
    }

    public float getAltezza() {
        return altezza.getPos();
    }
}
