package interpreter.typeConstr;

import command.Command;
import interpreter.CombinazioneIntermedia;
import interpreter.Path;
import interpreter.Simboli;
import interpreter.Type;

public class TypeConstrImg extends TypeConstr{
    private Path path;
    private Type img = new Type(Simboli.IMAGE);

    public TypeConstrImg(Path path) {
        this.path = path;
    }

    @Override
    public CombinazioneIntermedia interpreta(Command contesto, String input) {
        img.interpreta(contesto,input);
        path.interpreta(contesto,input);
        return this;
    }

    @Override
    public Type getTipo() {
        return img;
    }

    public String getPath(){
        return path.getPath();
    }
}
