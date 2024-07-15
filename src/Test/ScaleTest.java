package Test;

import command.Command;
import command.HistoryCommandHandler;
import command.specificcommand.ScaleCmd;
import id.IdGenerator;
import interpreter.ObjID;
import interpreter.PosFloat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shapes.model.CircleObject;
import shapes.model.RectangleObject;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScaleTest {
    private static HistoryCommandHandler historyCommandHandler;
    private static CircleObject circleObject;
    private static RectangleObject rectangleObject;
    static float raggioInit=80;
    static float baseInit=90;
    static float altInit=100;

    @BeforeAll
    public static void setUp() {
        historyCommandHandler = new HistoryCommandHandler();

        circleObject = new CircleObject(new Point2D.Double(100, 100),raggioInit);
        rectangleObject = new RectangleObject(new Point2D.Double(100, 100),baseInit,altInit);

        IdGenerator.ISTANCE.add(circleObject);
        IdGenerator.ISTANCE.add(rectangleObject);

        assertEquals(IdGenerator.ISTANCE.get(1),circleObject);
        assertEquals(IdGenerator.ISTANCE.get(2),rectangleObject);

    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void testScaleCircle(int factor){
        Command command = new ScaleCmd(new ObjID(1),new PosFloat(factor),null);

        historyCommandHandler.handle(command);

        assertEquals(raggioInit*factor,circleObject.getRadius());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void testScaleRect(int factor){
        Command command = new ScaleCmd(new ObjID(2),new PosFloat(factor),null);

        historyCommandHandler.handle(command);

        assertEquals(baseInit*factor,rectangleObject.getDimension().getWidth());
        assertEquals(altInit*factor,rectangleObject.getDimension().getHeight());
    }


}
