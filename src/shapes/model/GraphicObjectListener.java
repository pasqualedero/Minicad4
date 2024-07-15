
package shapes.model;

public interface GraphicObjectListener { //Observer -> gli ascoltatori ridisegnano l'oggetto grafico
	
	void graphicChanged(GraphicEvent e);
	
}
