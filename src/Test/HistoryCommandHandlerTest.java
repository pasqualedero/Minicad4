package Test;

import command.HistoryCommandHandler;
import id.IdGenerator;
import memento.MementoCapsule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapes.model.CircleObject;
import shapes.model.GraphicObject;
import shapes.model.RectangleObject;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryCommandHandlerTest {

    private HistoryCommandHandler historyCommandHandler;
    private GraphicObject circleObject;
    private GraphicObject rectangleObject;


    @BeforeEach
    public void setUp() {
        historyCommandHandler = new HistoryCommandHandler();

        circleObject = new CircleObject(new Point2D.Double(1.0, 2.0), 3.0);
        rectangleObject = new RectangleObject(new Point2D.Double(4.0, 5.0), 6.0, 7.0);

        IdGenerator.ISTANCE.add(circleObject);
        IdGenerator.ISTANCE.add(rectangleObject);

        assertEquals(IdGenerator.ISTANCE.get(1),circleObject);
        assertEquals(IdGenerator.ISTANCE.get(2),rectangleObject);

    }


    // Test su Handler e Memento
    @Test
    public void testUndo() {
         // Aggiungo oggetti in history
        MementoCapsule circleMemento = new MementoCapsule(circleObject, circleObject.getMemento());
        MementoCapsule rectangleMemento = new MementoCapsule(rectangleObject, rectangleObject.getMemento());

        historyCommandHandler.addToHistory(circleMemento);
        historyCommandHandler.addToHistory(rectangleMemento);

        // Verifico stato iniziale
        assertEquals(2, historyCommandHandler.getStackHistorySize());

        // Forzo un undo
        historyCommandHandler.undo();

        // Verifico corretta eliminazione da history
        assertEquals(1, historyCommandHandler.getStackHistorySize());

        // Verifico stato history dopo undo
        MementoCapsule topItem = historyCommandHandler.getTopItemFromHistory();
        assertEquals(circleObject, topItem.getGo());

    }

    @Test
    public void testAddToHistoryWithMaxSize() {
        HistoryCommandHandler handler = new HistoryCommandHandler(2);

        handler.addToHistory(new MementoCapsule(circleObject, circleObject.getMemento()));
        handler.addToHistory(new MementoCapsule(rectangleObject, rectangleObject.getMemento()));
        handler.addToHistory(new MementoCapsule(new CircleObject(new Point2D.Double(0.0, 0.0), 1.0), circleObject.getMemento()));

        assertEquals(2, handler.getStackHistorySize());
    }

}
