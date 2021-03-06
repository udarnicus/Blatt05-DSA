package de.unistuttgart.vis.dsass2020.ex05.p3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.unistuttgart.vis.dsass2020.ex05.p1.Rectangle;

/**
 * This class represents a two-dimensional collision map. A collision map is a
 * data structure that stores a set of rectangles. Given another rectangle, this
 * data structure allows retrieving all rectangles that intersect this
 * rectangle.
 *
 * @author Mohamed Ben Salha, 3465244,  st167263;
 * @author Radu Manea, 3465480, st166429;
 * @author Lars Gillich, 3465778, st167614;
 * @version 07.06.2020
 */
public class CollisionMap {

    // If the resolution of the grid is not specified by the user we use this
    // default resolution.
    private static final int GRID_RESOLUTION_X = 100;
    private static final int GRID_RESOLUTION_Y = 100;

    /**
     * Rectangle that encapsulates all rectangles in the collision map.
     */
    public Rectangle gridRectangle;

    /**
     * A two-dimensional array of {@link java.util.List} serves as the data
     * structure for storing the rectangles. Each element of the array holds a list
     * of rectangles. At the same time, each element of the array is associated with
     * an area of the bounding rectangle {@link CollisionMap.gridRectangle} through
     * the transform methods ({@link CollisionMap.transformX} and
     * {@link CollisionMap.transformY}. These areas are called cells.
     */
    private List<Rectangle>[][] map;

    /**
     * Creates a {@link CollisionMap} from a set of rectangles.
     *
     * @param rectangles that are placed in the collision map
     * @throws IllegalArgumentException
     */
    public CollisionMap(Set<Rectangle> rectangles) throws IllegalArgumentException {
        this(rectangles, GRID_RESOLUTION_X, GRID_RESOLUTION_Y);
    }

    /**
     * Creates a {@link CollisionMap} from a set of rectangles and specified grid
     * resolutions.
     *
     * @param rectangles      that are placed in the collision map
     * @param gridResolutionX
     * @param gridResolutionY
     * @throws IllegalArgumentException
     */
    public CollisionMap(Set<Rectangle> rectangles, int gridResolutionX, int gridResolutionY)
            throws IllegalArgumentException {
        if (rectangles == null || gridResolutionX < 1 || gridResolutionY < 1) {
            throw new IllegalArgumentException();
        }
        gridRectangle = Rectangle.getBoundingBox(rectangles);
        generateCollisionMap(gridResolutionX, gridResolutionY);
        try {
            fillCollisionMap(rectangles);
        } catch (CollisionMapOutOfBoundsException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Given a rectangle, this method returns a set of potential colliding
     * rectangles.
     * Since the given rectangle could partially lie outside the CollisionMap,
     * only the part that overlaps with the CollisionMap will be considered.
     * TopLeftX is x converted and rounded down
     * TopLefty is y converted and rounded up
     * BottomRightX is (x+width) converted and rounded up
     * BottomRightY is (y+height) converted and rounded down
     * so that all cells, even those who partially overlapping the given rectangle,
     * are also considered as collision candidates.
     *
     * @return set containig all rectangle placed on the cells overlapping
     * with the given rectangle
     * @throws CollisionMapOutOfBoundsException
     */
    private Set<Rectangle> getCollisionCandidates(Rectangle rectangle) throws CollisionMapOutOfBoundsException {
        final Set<Rectangle> set = new HashSet<>();

        if (intersectTwoRectangles(rectangle, gridRectangle) != null) {
            rectangle = intersectTwoRectangles(rectangle, gridRectangle);
        } else {
            return set;
        }

        int topLeftX = (int) Math.floor(transformX(rectangle.x));
        int bottomRightX = (int) Math.ceil(transformX(rectangle.x + rectangle.width));
        int topLeftY = (int) Math.floor(transformY(rectangle.y));
        int bottomRightY = (int) Math.ceil(transformY(rectangle.y + rectangle.height));
        for (int y = topLeftY; y < bottomRightY; ++y) {
            for (int x = topLeftX; x < bottomRightX; ++x) {
                for (int i = 0; i < map[y][x].size(); i++)
                    set.add(map[y][x].get(i));


            }
        }
        return set;
    }

    /**
     * Method calculates the intersection of two rectangles
     * <p>
     * Returns null if rectangles dont intersect
     *
     * @param rectangle1
     * @param rectangle2
     * @return the intersection of two rectangles when the intersect else nul
     */

    private Rectangle intersectTwoRectangles(Rectangle rectangle1, Rectangle rectangle2) {
        if (rectangle1.intersects(rectangle2)) {

            float XCoordinate = Math.max(rectangle1.x, rectangle2.x);
            float YCoordinate = Math.max(rectangle1.y, rectangle2.y);
            float width = Math.abs(XCoordinate - Math.min(rectangle1.x + rectangle1.width,
                    rectangle2.x + rectangle2.width));
            float height = Math.abs(YCoordinate - Math.min(rectangle1.y + rectangle1.height,
                    rectangle2.y + rectangle2.height));

            return new Rectangle(XCoordinate, YCoordinate, width, height);
        }

        return null;

    }


    /**
     * Fill this collision map with a set of rectangles.
     * TopLeftX is x converted and rounded down
     * TopLefty is y converted and rounded up
     * BottomRightX is (x+width) converted and rounded up
     * BottomRightY is (y+height) converted and rounded down
     * so that the rectangles which partially or completely overlapping with
     * a cell will be added to it.
     *
     * @param rectangles
     * @throws CollisionMapOutOfBoundsException
     */
    private void fillCollisionMap(Set<Rectangle> rectangles) throws CollisionMapOutOfBoundsException {

        for (final Rectangle rectangle : rectangles) {
            // in allen Zellen, die das Rechteck entweder teilweise oder
            // komplett abdeckt, wird das jeweilige  Rechteck in deren Liste eingefügt.
            int topLeftX = (int) Math.floor(transformX(rectangle.x));
            int bottomRightX = (int) Math.ceil(transformX(rectangle.x + rectangle.width));
            int topLeftY = (int) Math.floor(transformY(rectangle.y));
            int bottomRightY = (int) Math.ceil(transformY(rectangle.y + rectangle.height));
            for (int y = topLeftY; y < bottomRightY; ++y) {
                for (int x = topLeftX; x < bottomRightX; ++x) {
                    map[y][x].add(rectangle);
                }
            }
        }
    }

    /**
     * Transform a x coordinate from rectangle space to the internal space of the
     * {@link CollisionMap}. For accessing specific cells of the grid the return
     * value must be rounded and cast appropriately.
     *
     * @param x coordinate of a point
     * @return x coordinate of given point in the internal space
     * @throws CollisionMapOutOfBoundsException
     */
    private float transformX(float x) throws CollisionMapOutOfBoundsException {
        if (x < gridRectangle.x || x > gridRectangle.x + gridRectangle.width) {
            throw new CollisionMapOutOfBoundsException("x coordinate is outside the defined range.");
        } else {
            return ((x - gridRectangle.x) / gridRectangle.width) * map[0].length;
        }
    }

    /**
     * Transform a y coordinate from rectangle space to the internal space of the
     * {@link CollisionMap}. For accessing specific cells of the grid the return
     * value must be rounded and cast appropriately.
     *
     * @param y coordinate of a point
     * @return y coordinate of given point in the internal space
     * @throws CollisionMapOutOfBoundsException
     */
    private float transformY(float y) throws CollisionMapOutOfBoundsException {
        if (y < gridRectangle.y || y > gridRectangle.y + gridRectangle.height) {
            throw new CollisionMapOutOfBoundsException("y coordinate is outside the defined range.");
        } else {
            return ((y - gridRectangle.y) / gridRectangle.height) * map.length;
        }
    }

    /**
     * @param rectangle
     * @return true iff the given rectangle intersects one of the rectangles in the
     * collision map.
     */
    public boolean collide(Rectangle rectangle) throws CollisionMapOutOfBoundsException {

        return getCollisionCandidates(rectangle).stream().filter(rechteck -> rechteck.intersects(rectangle)).collect(Collectors.toList()).size() > 0;
    }

    /**
     * Allocate the collision map
     *
     * @param gridResolutionX
     * @param gridResolutionY
     */
    @SuppressWarnings("unchecked")
    private void generateCollisionMap(int gridResolutionX, int gridResolutionY) {
        map = new ArrayList[gridResolutionY][gridResolutionX];
        for (int y = 0; y < gridResolutionY; ++y) {
            for (int x = 0; x < gridResolutionX; ++x) {
                map[y][x] = new ArrayList<Rectangle>();
            }
        }
    }


}