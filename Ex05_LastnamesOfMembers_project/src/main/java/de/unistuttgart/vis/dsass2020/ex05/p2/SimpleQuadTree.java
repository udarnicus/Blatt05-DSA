package de.unistuttgart.vis.dsass2020.ex05.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.unistuttgart.vis.dsass2020.ex05.p1.Point;
import de.unistuttgart.vis.dsass2020.ex05.p1.Rectangle;
import de.unistuttgart.vis.dsass2020.ex05.p2.QuadTree;

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
     *
     */
    return Rectangle.getBoundingBox( elements.stream().map(s->new Rectangle(s.getPosition().x,s.getPosition().y,0
            ,0)).collect(Collectors.toList()));


  }


  void createQuadTree(final List<T> list) throws IllegalArgumentException {
    Rectangle boundingBox = computeBoundingBox(list);
    if(list.size() <= 1){
      this.leafElements = list;
      return;
    }
    Rectangle topLeftBoundingBox = new Rectangle(boundingBox.x, boundingBox.y, boundingBox.width/2,
            boundingBox.height/2);
    Rectangle topRightBoundingBox = new Rectangle( boundingBox.x + boundingBox.width/2, boundingBox.y,
            boundingBox.width/2,
            boundingBox.height/2);
    Rectangle bottomLeftBoundingBox = new Rectangle(boundingBox.x, boundingBox.y + boundingBox.height/2,
            boundingBox.width/2, boundingBox.height/2);
    Rectangle bottomRightBoundingBox = new Rectangle(boundingBox.x + boundingBox.width/2,
            boundingBox.y + boundingBox.height/2,
            boundingBox.width/2, boundingBox.height/2);

    ArrayList<Point> pointsInTopLeftBoundingBox = new ArrayList<>();
    ArrayList<Point> pointsInTopRightBoundingBox = new ArrayList<>();
    ArrayList<Point> pointsInBottomLeftBoundingBox = new ArrayList<>();
    ArrayList<Point> pointsInBottomRightBoundingBox = new ArrayList<>();

    for(T element: list){
      //if element in boundingbox then add to array
    }

    //liste in 4 bereiche unterteilen

    this.bl = new SimpleQuadTree<T>(liste1, this.maxLeafElements);
    this.bl.createQuadTree(liste1);




  }

  @Override
  public void rangeQuery(final List<T> resultList, final Rectangle query) {
    // TODO Insert code for assignment 5.2.c

  }
}
