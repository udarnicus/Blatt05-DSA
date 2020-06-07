package de.unistuttgart.vis.dsass2020.ex05.p2;

import de.unistuttgart.vis.dsass2020.ex05.p1.Point;

/**
 * Implementation of the {@link QuadTreeElement } so that the abstract methods
 * can be implemented and the methods can be tested.
 *
 * @author Mohamed Ben Salha, 3465244,  st167263;
 * @author Radu Manea, 3465480, st166429;
 * @author Lars Gillich, 3465778, st167614;
 * @version 07.06.2020
 */
public class QuadTreeElementImplementation implements QuadTreeElement {
    private Point location;
    private String value;

    public QuadTreeElementImplementation(final Point location, final String value) {
        setValue(value);
        setLocation(location);
    }


    @Override
    public Point getPosition() {
        return location;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
