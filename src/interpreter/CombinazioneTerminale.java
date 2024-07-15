package interpreter;

import command.Command;
import shapes.view.GraphicObjectPanel;

public interface CombinazioneTerminale {
    public Command interpreta(GraphicObjectPanel contesto, String input);
}
