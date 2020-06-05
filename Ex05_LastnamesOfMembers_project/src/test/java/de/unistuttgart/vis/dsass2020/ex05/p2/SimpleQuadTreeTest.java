package de.unistuttgart.vis.dsass2020.ex05.p2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.unistuttgart.vis.dsass2020.ex05.p1.Point;
import de.unistuttgart.vis.dsass2020.ex05.p1.Rectangle;

public class SimpleQuadTreeTest {
    @Test
    public void testComputeBoundingBox(){
        Point point1 = new Point(1,2);
        QuadTreeElementImplementation element1 = new QuadTreeElementImplementation(point1, "lala");
        Point point2 = new Point(10,12);
        QuadTreeElementImplementation element2 = new QuadTreeElementImplementation(point2, "asdf");
        Point point3 = new Point(5,15);
        QuadTreeElementImplementation element3 = new QuadTreeElementImplementation(point3, "axx");
        Point point4 = new Point(20,0);
        QuadTreeElementImplementation element4 = new QuadTreeElementImplementation(point4, "lalbvnma");
        ArrayList<QuadTreeElementImplementation> points = new ArrayList<>();
        points.add(element1);
        points.add(element2);
        points.add(element3);
        points.add(element4);
        SimpleQuadTree<QuadTreeElementImplementation> quadTree = new SimpleQuadTree<>(points,1);
        assertEquals(1, (int) quadTree.getBoundingBox().x);
        assertEquals(0, (int) quadTree.getBoundingBox().y);
        assertEquals(19, (int) quadTree.getBoundingBox().width);
        assertEquals(15, (int) quadTree.getBoundingBox().height);


    }

    @Test
    public void testCreateQuadTree(){
        Point point1 = new Point(1,2);
        QuadTreeElementImplementation element1 = new QuadTreeElementImplementation(point1, "lala");
        Point point2 = new Point(10,12);
        QuadTreeElementImplementation element2 = new QuadTreeElementImplementation(point2, "asdf");
        Point point3 = new Point(5,15);
        QuadTreeElementImplementation element3 = new QuadTreeElementImplementation(point3, "axx");
        Point point4 = new Point(20,0);
        QuadTreeElementImplementation element4 = new QuadTreeElementImplementation(point4, "lalbvnma");
        ArrayList<QuadTreeElementImplementation> points = new ArrayList<>();
        points.add(element1);
        points.add(element2);
        points.add(element3);
        points.add(element4);
        SimpleQuadTree<QuadTreeElementImplementation> quadTree = new SimpleQuadTree<>(points,1);
    }

    @Test
    public void testRangeQuery(){
        Point point1 = new Point(1,2);
        QuadTreeElementImplementation element1 = new QuadTreeElementImplementation(point1, "lala");
        Point point2 = new Point(10,12);
        QuadTreeElementImplementation element2 = new QuadTreeElementImplementation(point2, "asdf");
        Point point3 = new Point(5,15);
        QuadTreeElementImplementation element3 = new QuadTreeElementImplementation(point3, "axx");
        Point point4 = new Point(20,0);
        QuadTreeElementImplementation element4 = new QuadTreeElementImplementation(point4, "lalbvnma");
        ArrayList<QuadTreeElementImplementation> points = new ArrayList<>();
        points.add(element1);
        points.add(element2);
        points.add(element3);
        points.add(element4);

        SimpleQuadTree<QuadTreeElementImplementation> quadTree = new SimpleQuadTree<>(points,1);
        ArrayList<QuadTreeElementImplementation> list = new ArrayList<>();
        quadTree.rangeQuery(list,quadTree.boundingBox);
        assertEquals(4,list.size());
    }


}
