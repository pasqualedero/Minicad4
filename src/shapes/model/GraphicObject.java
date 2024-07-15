package shapes.model;

import interpreter.typeConstr.TypeConstr;
import memento.Originator;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public interface GraphicObject extends Originator { //SUBJECT del pattern Observer

	void addGraphicObjectListener(GraphicObjectListener l);

	void removeGraphicObjectListener(GraphicObjectListener l);

	void moveTo(Point2D p);

	default void moveTo(double x, double y){
		moveTo(new Point2D.Double(x, y));
	}

	Point2D getPosition();

	void setDimension(TypeConstr t);

	Dimension2D getDimension();

	void scale(double factor);

	boolean contains(Point2D p); // se punto è contenuto nella figura

	double area();

	double perimetro();

	String getType();

	GraphicObject clone();


}
