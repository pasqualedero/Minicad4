package interpreter.typeConstr;

import command.Command;
import interpreter.CombinazioneIntermedia;
import interpreter.PosFloat;
import interpreter.Simboli;
import interpreter.Type;

public class TypeConstrCircle extends TypeConstr{
    private PosFloat raggio;
    Type tipo = new Type(Simboli.CIRCLE);

    public TypeConstrCircle(PosFloat raggio) {
        this.raggio = raggio;
    }

    public float getRaggio() {
        return raggio.getPos();
    }

    public Type getTipo() {
        return tipo;
    }

    @Override
    public CombinazioneIntermedia interpreta(Command contesto, String input) {
        tipo.interpreta(contesto,input);
        raggio.interpreta(contesto,input);
        return this;
    }
}
