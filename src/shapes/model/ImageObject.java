package shapes.model;

import interpreter.typeConstr.TypeConstr;
import memento.Memento;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public final class ImageObject extends AbstractGraphicObject {
	private double factor = 1.0;

	private final Image image;

	private Point2D position;

	public Image getImage() {
		return image;
	}

	public ImageObject(ImageIcon img, Point2D pos) {
		position = new Point2D.Double(pos.getX(), pos.getY());
		image = img.getImage();
	}

	@Override
	public boolean contains(Point2D p) {
		double w = (factor * image.getWidth(null)) / 2;
		double h = (factor * image.getHeight(null)) / 2;
		double dx = Math.abs(p.getX() - position.getX());
		double dy = Math.abs(p.getY() - position.getY());
		return dx <= w && dy <= h;
	}

	@Override
	public double area() {
		Dimension2D dim = getDimension();
		return dim.getWidth() * dim.getHeight();
	}

	@Override
	public double perimetro() {
		Dimension2D dim = getDimension();
		return 2*(dim.getHeight())+ dim.getWidth();
	}

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public ImageObject clone() {
		ImageObject cloned = (ImageObject) super.clone();
		cloned.position = (Point2D) position.clone();
		return cloned;

	}

	@Override
	public Point2D getPosition() {

		return new Point2D.Double(position.getX(), position.getY());
	}

	@Override
	public void setDimension(TypeConstr t) {
		// Non fa nulla
	}

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		this.factor *= factor;
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension dim = new Dimension();
		dim.setSize(factor * image.getWidth(null),
				factor * image.getHeight(null));
		return dim;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.shapes.GraphicObject#getType()
	 */
	@Override
	public String getType() {

		return "Image";
	}

	@Override
	public Memento getMemento() {
		return new ImageMemento(getPosition(),factor);
	}

	@Override
	public void setMemento(Memento m) {
		if (m instanceof ImageMemento im){
			this.factor= im.factor;
			this.position.setLocation(im.position);
			notifyListeners(new GraphicEvent(this));
		}else
			throw new IllegalArgumentException("Memento non valido");
	}

	private record ImageMemento(Point2D position, double factor) implements Memento{ // ometto il campo Image

	}

//	@Override
//	public String toString() {
//		return "IMAGE - height: "+getDimension().getHeight()+", width: " + getDimension().getWidth() +", position: ("+position.getX()+", "+position.getY()+")";
//	}

	private static final String TO_STRING_FORMAT = "IMAGE - height: %.2f, width: %.2f, position: (%.2f, %.2f)";

	@Override
	public String toString() {
		Dimension2D dim = getDimension();
		return String.format(TO_STRING_FORMAT, dim.getHeight(), dim.getWidth(), position.getX(), position.getY());
	}
}
