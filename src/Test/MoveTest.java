package Test;

import command.Command;
import command.HistoryCommandHandler;
import command.specificcommand.MoveCmd;
import id.IdGenerator;
import interpreter.ObjID;
import interpreter.Pos;
import interpreter.PosFloat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shapes.model.CircleObject;
import shapes.model.GraphicObject;
import shapes.model.RectangleObject;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest {
    private static HistoryCommandHandler historyCommandHandler;
    private static GraphicObject circleObject;
    private static GraphicObject rectangleObject;
    static Point2D posInit = new Point2D.Double(100, 100);
    Pos posFinale = new Pos(new PosFloat(200),new PosFloat(350));

    @BeforeAll
    public static void setUp() {
        historyCommandHandler = new HistoryCommandHandler();

        circleObject = new CircleObject(posInit,80);
        rectangleObject = new RectangleObject(posInit,100,300);

        IdGenerator.ISTANCE.add(circleObject);
        IdGenerator.ISTANCE.add(rectangleObject);

        assertEquals(IdGenerator.ISTANCE.get(1),circleObject);
        assertEquals(IdGenerator.ISTANCE.get(2),rectangleObject);

    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void testMove(int id){
        System.out.println(id);
        Command command = new MoveCmd(false,new ObjID(id),posFinale,null);

        historyCommandHandler.handle(command);

        assertEquals(posFinale.getX().getPos(),IdGenerator.ISTANCE.get(id).getPosition().getX());
        assertEquals(posFinale.getY().getPos(),IdGenerator.ISTANCE.get(id).getPosition().getY());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void testMoveOff(int id){
        System.out.println(id);
        Command command = new MoveCmd(true,new ObjID(id),posFinale,null);

        historyCommandHandler.handle(command);

        assertEquals(300,IdGenerator.ISTANCE.get(id).getPosition().getX());
        assertEquals(450,IdGenerator.ISTANCE.get(id).getPosition().getY());
    }

}
