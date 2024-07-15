package shapes.model;

import interpreter.typeConstr.TypeConstr;
import interpreter.typeConstr.TypeConstrRectange;
import memento.Memento;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.Objects;

public final class RectangleObject extends AbstractGraphicObject {

	private Point2D position;

	private Dimension2D dim;

	public RectangleObject(Point2D pos, double w, double h) {
		if (w <= 0 || h <= 0)
			throw new IllegalArgumentException();
		dim = new Dimension();
		dim.setSize(w, h);
		position = new Point2D.Double(pos.getX(), pos.getY());

	}

	@Override
	public boolean contains(Point2D p) {
		double w = dim.getWidth() / 2;
		double h = dim.getHeight() / 2;
		double dx = Math.abs(p.getX() - position.getX());
		double dy = Math.abs(p.getY() - position.getY());
		return dx <= w && dy <= h;

	}

	@Override
	public double area() {
		return dim.getHeight()* dim.getWidth();
	}

	@Override
	public double perimetro() {
		return 2*(dim.getHeight())+2*(dim.getWidth());
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
		TypeConstrRectange tcr = (TypeConstrRectange) t;
		dim.setSize(tcr.getBase(),tcr.getAltezza());
		notifyListeners(new GraphicEvent(this));
	}


	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		dim.setSize(dim.getWidth() * factor, dim.getHeight() * factor);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension2D d = new Dimension();
		d.setSize(dim);
		return d;
	}

	@Override
	public RectangleObject clone() {
		RectangleObject cloned = (RectangleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		cloned.dim = (Dimension2D) dim.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Rectangle";
	}

	@Override
	public Memento getMemento() {
		return new RectangleMemento(getPosition(),getDimension());
	}

	@Override
	public void setMemento(Memento m) {
		if (m instanceof RectangleMemento rm){
			double b = rm.dimension.getWidth();
			double h = rm.dimension.getHeight();
			this.dim.setSize(b,h);
			this.position.setLocation(rm.position);
			notifyListeners(new GraphicEvent(this));
		}else
			throw new IllegalArgumentException("Memento non valido");
	}

	private record RectangleMemento(Point2D position, Dimension2D dimension) implements Memento{

	}

	private static final String TO_STRING_FORMAT = "RECTANGLE - height: %.2f, width: %.2f, position: (%.2f, %.2f)";

	@Override
	public String toString() {
		Dimension2D dim = getDimension();
		return String.format(TO_STRING_FORMAT, dim.getHeight(), dim.getWidth(), position.getX(), position.getY());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RectangleObject that = (RectangleObject) o;
		return Objects.equals(position, that.position) && Objects.equals(dim, that.dim);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, dim);
	}
}
