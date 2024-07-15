package Test;

import command.Command;
import command.HistoryCommandHandler;
import command.specificcommand.perimeterCmd.PerimeterIdCmd;
import id.IdGenerator;
import interpreter.ObjID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shapes.model.CircleObject;
import shapes.model.RectangleObject;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerimeterTest {
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
    public void PerimeterArea(int id){
        Command command = new PerimeterIdCmd(new ObjID(id),null);

        historyCommandHandler.handle(command);

        if (id==1) {
            assertEquals(Math.PI*raggioInit*2, circleObject.perimetro());
        }else assertEquals(baseInit*2+2*altInit, rectangleObject.perimetro());
    }


}
