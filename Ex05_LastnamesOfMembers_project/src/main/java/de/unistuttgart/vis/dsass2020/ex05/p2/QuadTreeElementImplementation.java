package de.unistuttgart.vis.dsass2020.ex05.p2;

import de.unistuttgart.vis.dsass2020.ex05.p1.Point;

public class QuadTreeElementImplementation implements QuadTreeElement {
    private Point location;
    private String value;

    public QuadTreeElementImplementation(Point location, String value){
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
