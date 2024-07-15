package shapes.view;

import shapes.model.GraphicEvent;
import shapes.model.GraphicObject;
import shapes.model.GraphicObjectListener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class GraphicObjectPanel extends JComponent implements GraphicObjectListener { // è un observer

	private static final long serialVersionUID = 8993548105090978185L;

	private final List<GraphicObject> objects = new LinkedList<>(); // tutti oggetti del pannello


	public GraphicObjectPanel() {
		setBackground(Color.WHITE);
	}

	@Override
	public void graphicChanged(GraphicEvent e) { // quello che deve fare il listener (this): ridisegnare
		repaint();
		revalidate();

	}

	@Override
	public Dimension getPreferredSize() {
		Dimension ps = super.getPreferredSize();
		double x = ps.getWidth();
		double y = ps.getHeight();
		for (GraphicObject go : objects) {
			double nx = go.getPosition().getX() + go.getDimension().getWidth() / 2;
			double ny = go.getPosition().getY() + go.getDimension().getHeight() / 2;
			if (nx > x)
				x = nx;
			if (ny > y)
				y = ny;
		}
		return new Dimension((int) x, (int) y);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		for (GraphicObject go : objects) {
			GraphicObjectView view = GraphicObjectViewFactory.FACTORY.createView(go);
			view.drawGraphicObject(go, g2);
		}

	}

	public void add(GraphicObject go) {
		objects.add(go);
		go.addGraphicObjectListener(this); // mi registro come ascoltatore
		repaint();
	}

	public void remove(GraphicObject go) {
		if (objects.remove(go)) {
			repaint();
			go.removeGraphicObjectListener(this);
		}

	}

	
}
