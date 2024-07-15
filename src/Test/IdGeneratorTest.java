package Test;

import id.IdGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import shapes.model.CircleObject;
import shapes.model.GraphicObject;
import shapes.model.RectangleObject;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

public class IdGeneratorTest {

    @AfterEach
    public void clean() {
        IdGenerator.ISTANCE.clear();
    }

    @Test
    public void testAddAndGet() {
        IdGenerator generator = IdGenerator.ISTANCE;

        GraphicObject rect = new RectangleObject(new Point2D.Double(3, 1), 4, 6);
        GraphicObject circle = new CircleObject(new Point2D.Double(6, 5), 8);

        int rectId = generator.add(rect);
        int circleId = generator.add(circle);

        assertEquals(rect, generator.get(rectId));
        assertEquals(circle, generator.get(circleId));
    }

    @Test
    public void testDelete() {
        IdGenerator generator = IdGenerator.ISTANCE;

        GraphicObject rect = new RectangleObject(new Point2D.Double(3, 1), 4, 6);
        int rectId = generator.add(rect);

        generator.delete(rectId);

        assertThrows(exception.InvalidID.class, () -> {
            generator.get(rectId);
        });
    }

    @Test
    public void testGetInvalidID() {
        IdGenerator generator = IdGenerator.ISTANCE;

        assertThrows(exception.InvalidID.class, () -> {
            generator.get(-1);
        });
    }
}
