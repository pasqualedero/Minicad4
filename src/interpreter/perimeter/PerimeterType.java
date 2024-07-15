package interpreter.perimeter;

import command.Command;
import command.specificcommand.perimeterCmd.PerimeterTypeCmd;
import interpreter.CombinazioneIntermedia;
import interpreter.Type;
import shapes.view.GraphicObjectPanel;

public class PerimeterType extends Perimeter {
    private Type type;

    public PerimeterType(Type type) {
        this.type = type;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia tipo = type.interpreta(null,input);
        return new PerimeterTypeCmd((Type) tipo,contesto);
    }
}
