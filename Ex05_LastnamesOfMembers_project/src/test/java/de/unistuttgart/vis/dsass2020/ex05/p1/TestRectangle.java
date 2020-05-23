package de.unistuttgart.vis.dsass2020.ex05.p1;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import de.unistuttgart.vis.dsass2020.ex05.p1.Point;

import java.util.ArrayList;


public class TestRectangle {

    @Test
    public void testContains(){
        Point point1 = new Point(10, 10);
        Point point2 = new Point(15, 10);
        Point point3 = new Point(5, 4);

        Rectangle rectangle = new Rectangle(5, 5 , 10, 10);
        assertTrue(rectangle.contains(point1));
        assertTrue(rectangle.contains(point2));
        assertFalse(rectangle.contains(point3));
    }

    @Test
    public void testIntersects(){

        Rectangle rectangle1 = new Rectangle(5, 5 , 10, 10);
        Rectangle rectangle2 = new Rectangle(15, 15 , 10, 10);
        Rectangle rectangle3 = new Rectangle(8, 8 , 1, 1);
        Rectangle rectangle4 = new Rectangle(16, 16 , 5, 5);

        assertTrue(rectangle1.intersects(rectangle2));
        assertTrue(rectangle1.intersects(rectangle3));
        assertFalse(rectangle1.intersects(rectangle4));
    }

    @Test
    public void testExtendTo(){

        Rectangle rectangle1 = new Rectangle(5, 5 , 10, 10);
        Point point1 = new Point(100, 100);
        Point point2 = new Point(10, 10);
        Point point3 = new Point(0, 0);

        ArrayList<Point> array1 = new ArrayList<>();
        array1.add(point1);
        ArrayList<Point> array2 = new ArrayList<>();
        array2.add(point2);
        ArrayList<Point> array3 = new ArrayList<>();
        array3.add(point1);
        array3.add(point3);


        Rectangle newRectangle1 = rectangle1.extendTo(array1);
        Rectangle newRectangle2 = rectangle1.extendTo(array2);
        Rectangle newRectangle3 = rectangle1.extendTo(array3);

        assertEquals(95.0, newRectangle1.width );
        assertEquals(95.0, newRectangle1.height );
        assertEquals(10.0, newRectangle2.width );
        assertEquals(10.0, newRectangle2.height );
        assertEquals(0.0, newRectangle3.x );
        assertEquals(0.0, newRectangle3.y );
        assertEquals(100.0, newRectangle3.width );
        assertEquals(100.0, newRectangle3.height );
    }
}
