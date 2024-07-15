package command;

import shapes.model.GraphicObject;

public interface Command {
	boolean doIt();

	GraphicObject getGraphicObject();
}
