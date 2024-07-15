package interpreter.area;

import command.Command;
import command.specificcommand.areaCmd.AreaTypeCmd;
import interpreter.CombinazioneIntermedia;
import interpreter.Type;
import shapes.view.GraphicObjectPanel;

public class AreaType extends Area{
    private Type type;

    public AreaType(Type type) {
        this.type = type;
    }

    @Override
    public Command interpreta(GraphicObjectPanel contesto, String input) {
        CombinazioneIntermedia combinazioneIntermedia = type.interpreta(null,input);
        return new AreaTypeCmd((Type) combinazioneIntermedia, contesto);
    }
}
