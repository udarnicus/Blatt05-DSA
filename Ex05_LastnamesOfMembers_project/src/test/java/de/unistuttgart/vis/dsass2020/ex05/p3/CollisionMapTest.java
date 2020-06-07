package de.unistuttgart.vis.dsass2020.ex05.p3;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.unistuttgart.vis.dsass2020.ex05.p1.Rectangle;

public class CollisionMapTest {
    @Test
    public void testFillCollisionMap(){
        Rectangle rectangle1 = new Rectangle(1234,1234,10,10);
        Rectangle rectangle2 = new Rectangle(5,9,12,10);
        Rectangle rectangle3 = new Rectangle(6,11,10,2);
        Rectangle rectangle4 = new Rectangle(3,3,1,20);
        Rectangle rectangle5 = new Rectangle(1234,1234,10,10);

        Set<Rectangle> rectangles = new HashSet<>();
        rectangles.add(rectangle1);
        rectangles.add(rectangle2);
        rectangles.add(rectangle3);
        rectangles.add(rectangle4);
        rectangles.add(rectangle5);

        CollisionMap collisionMap = new CollisionMap(rectangles);


    }


}
