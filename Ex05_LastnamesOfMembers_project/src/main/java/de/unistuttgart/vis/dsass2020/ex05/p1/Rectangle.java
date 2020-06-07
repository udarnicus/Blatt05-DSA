package de.unistuttgart.vis.dsass2020.ex05.p1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * This class represents a quadrilateral, where the angles are 90 degrees.
 *
 * @author Mohamed Ben Salha, 3465244,  st167263;
 * @author Radu Manea, 3465480, st166429;
 * @author Lars Gillich, 3465778, st167614;
 * @version 07.06.2020
 */
public class Rectangle {

    // Upper left corner of the rectangle
    public final float x;
    public final float y;

    public final float width;
    public final float height;

    /**
     * Generate a new rectangle
     *
     * @param x      left position of the rectangle
     * @param y      upper position of the rectangle
     * @param width
     * @param height
     */
    public Rectangle(float x, float y, float width, float height) {
        // enforce positive width
        if (width >= 0) {
            this.x = x;
            this.width = width;
        } else {
            this.x = x + width;
            this.width = -width;
        }
        // enforce positive height
        if (height >= 0) {
            this.y = y;
            this.height = height;
        } else {
            this.y = y + height;
            this.height = -height;
        }
    }

    /**
     * @param point
     * @return true if the point is inside the rectangle or on its edge
     */
    public boolean contains(final Point point) {
        boolean containsX = point.x >= this.x && point.x <= this.x + this.width;
        boolean containsY = point.y >= this.y && point.y <= this.y + this.height;

        return (containsX && containsY);
    }

    /**
     *
     * @param rectangle
     * @return a list containing the four corner points of the given rectangle
     */
    private ArrayList<Point> getCornerPoints(final Rectangle rectangle) {
        final ArrayList<Point> cornersOfGivenRectangle = new ArrayList<>();
        cornersOfGivenRectangle.add(new Point(rectangle.x, rectangle.y));
        cornersOfGivenRectangle.add(new Point(rectangle.x + rectangle.width, rectangle.y));
        cornersOfGivenRectangle.add(new Point(rectangle.x, rectangle.y + rectangle.height));
        cornersOfGivenRectangle.add(new Point(rectangle.x + rectangle.width, rectangle.y + rectangle.height));
        return cornersOfGivenRectangle;
    }

    /**
     * @param rectangle
     * @return true iff the rectangle intersects this rectangle
     */
    public boolean intersects(final Rectangle rectangle) {
        ArrayList<Point> cornersOfCurrentRectangle = getCornerPoints(this);
        ArrayList<Point> cornersOfGivenRectangle = getCornerPoints(rectangle);

        boolean intersectionOfRectangles = false;

        for (Point point : cornersOfCurrentRectangle) {
            if (rectangle.contains(point)) {
                intersectionOfRectangles = true;
            }
        }
        for (Point point : cornersOfGivenRectangle) {
            if (this.contains(point)) {
                intersectionOfRectangles = true;
            }
        }
        return intersectionOfRectangles;


    }

    /**
     * Compute the bounding rectangle for a set of rectangles.
     *
     * @param rectangles
     * @return the bounding rectangle
     */
    public static Rectangle getBoundingBox(final Collection<Rectangle> rectangles) {
        float minX = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;
        for (Rectangle rectangle : rectangles) {
            if (rectangle.x < minX) {
                minX = rectangle.x;
            }
            if (rectangle.x + rectangle.width > maxX) {
                maxX = rectangle.x + rectangle.width;
            }
            if (rectangle.y < minY) {
                minY = rectangle.y;
            }
            if (rectangle.y + rectangle.height > maxY) {
                maxY = rectangle.y + rectangle.height;
            }
        }
        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

    /**
     * Extends the rectangle to cover a set of points.
     *
     * @param points
     * @return the grown rectangle that includes all {points}.
     */
    public Rectangle extendTo(final Collection<Point> points) {
        final ArrayList<Point> cornersOfCurrentRectangle = getCornerPoints(this);
        final ArrayList<Float> listOfXCoordinates = new ArrayList<>();
        final ArrayList<Float> listOfYCoordinates = new ArrayList<>();

        points.addAll(cornersOfCurrentRectangle);

        for (final Point point : points) {
            listOfXCoordinates.add(point.x);
            listOfYCoordinates.add(point.y);
        }

        return new Rectangle(Collections.min(listOfXCoordinates), Collections.min(listOfYCoordinates),
                Collections.max(listOfXCoordinates) - Collections.min(listOfXCoordinates),
                Collections.max(listOfYCoordinates) - Collections.min(listOfYCoordinates));
    }
}