package de.unistuttgart.vis.dsass2020.ex05.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import de.unistuttgart.vis.dsass2020.ex05.p1.Rectangle;

public class SimpleQuadTree<T extends QuadTreeElement> extends QuadTree<T> {

    public SimpleQuadTree(final List<T> elements, final int maxElementsInLeaf)
            throws IllegalArgumentException {
        if (elements == null || maxElementsInLeaf < 1) {
            throw new IllegalArgumentException();
        } else {
            boundingBox = computeBoundingBox(elements);
            this.maxLeafElements = maxElementsInLeaf;
            createQuadTree(elements);
        }
    }

    private SimpleQuadTree(final List<T> elements, final int maxElementsInLeaf,
                           final Rectangle boundingBox) throws IllegalArgumentException {
        this.boundingBox = boundingBox;
        this.maxLeafElements = maxElementsInLeaf;
        createQuadTree(elements);
    }

    private Rectangle computeBoundingBox(final List<T> elements) {
        /**
         * Returns bounding box of all points
         *
         * map every element to the rectangle that is based on the elements position
         */
        return Rectangle.getBoundingBox(elements.stream().map(element -> new Rectangle(element.getPosition().x, element.getPosition().y, 0
                , 0)).collect(Collectors.toList()));
    }


    void createQuadTree(final List<T> list) throws IllegalArgumentException {
        Rectangle boundingBox = computeBoundingBox(list);
        if (list.size() <= maxLeafElements) { // hier bin ich mir nicht sicher, ob es die Rekursionsbremse ist
            this.leafElements = list;
            return;
        }
        final Rectangle topLeftBoundingBox = new Rectangle(boundingBox.x, boundingBox.y, boundingBox.width / 2,
                boundingBox.height / 2);
        final Rectangle topRightBoundingBox = new Rectangle(boundingBox.x + boundingBox.width / 2, boundingBox.y,
                boundingBox.width / 2,
                boundingBox.height / 2);
        final Rectangle bottomLeftBoundingBox = new Rectangle(boundingBox.x, boundingBox.y + boundingBox.height / 2,
                boundingBox.width / 2, boundingBox.height / 2);
        final Rectangle bottomRightBoundingBox = new Rectangle(boundingBox.x + boundingBox.width / 2,
                boundingBox.y + boundingBox.height / 2,
                boundingBox.width / 2, boundingBox.height / 2);

        List<T> pointsInTopLeftBoundingBox = new ArrayList<>();
        List<T> pointsInTopRightBoundingBox = new ArrayList<>();
        List<T> pointsInBottomLeftBoundingBox = new ArrayList<>();
        List<T> pointsInBottomRightBoundingBox = new ArrayList<>();

        for (final T element : list) {
            if (topLeftBoundingBox.contains(element.getPosition())) {
                pointsInTopLeftBoundingBox.add(element);
            } else if (topRightBoundingBox.contains(element.getPosition())) {
                pointsInTopRightBoundingBox.add(element);
            } else if (bottomLeftBoundingBox.contains(element.getPosition())) {
                pointsInBottomLeftBoundingBox.add(element);
            } else {
                pointsInBottomRightBoundingBox.add(element);
            }

        }

        //liste in 4 Bereiche unterteilen

        this.bl = new SimpleQuadTree<T>(pointsInBottomLeftBoundingBox, this.maxLeafElements, bottomLeftBoundingBox);
        this.br = new SimpleQuadTree<T>(pointsInBottomRightBoundingBox, this.maxLeafElements, bottomRightBoundingBox);
        this.tl = new SimpleQuadTree<T>(pointsInTopLeftBoundingBox, this.maxLeafElements, topLeftBoundingBox);
        this.tr = new SimpleQuadTree<T>(pointsInTopRightBoundingBox, this.maxLeafElements, topRightBoundingBox);

    }

    @Override
    public void rangeQuery(final List<T> resultList, final Rectangle query) {
        if (this.leafElements.size() <= maxLeafElements) {
            //check if every point is in rectangle
            for (final T element : this.leafElements) {
                if (query.contains(element.getPosition())) {
                    resultList.add(element);
                }
            }
        }
        if (this.tl.boundingBox.intersects(query)) {
            this.tl.rangeQuery(resultList, query);
        }
        if (this.tr.boundingBox.intersects(query)) {
            this.tr.rangeQuery(resultList, query);
        }
        if (this.bl.boundingBox.intersects(query)) {
            this.bl.rangeQuery(resultList, query);
        }
        if (this.br.boundingBox.intersects(query)) {
            this.br.rangeQuery(resultList, query);
        }


    }
}
