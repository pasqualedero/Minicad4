package shapes.model;

import interpreter.typeConstr.TypeConstr;
import interpreter.typeConstr.TypeConstrCircle;
import memento.Memento;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.Objects;

public final  class CircleObject extends AbstractGraphicObject {

	private Point2D position;

	private double radius;

	public CircleObject(Point2D pos, double r) {
		if (r <= 0)
			throw new IllegalArgumentException();
		position = new Point2D.Double(pos.getX(), pos.getY());
		radius = r;
	}

	

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Point2D getPosition() {

		return new Point2D.Double(position.getX(), position.getY());
	}

	@Override
	public void setDimension(TypeConstr t) {
		TypeConstrCircle tcc = (TypeConstrCircle) t;
		this.radius = tcc.getRaggio();
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		radius *= factor;
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension d = new Dimension();
		d.setSize(2 * radius, 2 * radius);

		return d;
	}

	@Override
	public boolean contains(Point2D p) {
		return (position.distance(p) <= radius);

	}

	@Override
	public double area() {
		return Math.PI*Math.pow(radius,2);
	}

	@Override
	public double perimetro() {
		return 2*Math.PI*radius;
	}

	@Override
	public CircleObject clone() {
		CircleObject cloned = (CircleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Circle";
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public Memento getMemento() {
		return new CircleMemento(getPosition(),this.radius);
	}

	public void setMemento(Memento m){
		if (m instanceof CircleMemento cm){
			radius=cm.radius;
			position.setLocation(cm.position);
			notifyListeners(new GraphicEvent(this));
		}else
			throw new IllegalArgumentException("Memento non valido");
	}

	private record CircleMemento(Point2D position, double radius) implements Memento{

	}


	@Override
	public String toString() {
		return String.format("CIRCLE - radius: %.2f, position: (%.2f, %.2f)", radius, position.getX(), position.getY());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CircleObject that = (CircleObject) o;
		return Double.compare(radius, that.radius) == 0 && Objects.equals(position, that.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, radius);
	}
}
