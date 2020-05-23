package de.unistuttgart.vis.dsass2020.ex05.p2;

import java.util.ArrayList;
import java.util.List;

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
    // TODO Insert code for assignment 5.2.a
  }

  void createQuadTree(final List<T> list) throws IllegalArgumentException {
    // TODO Insert code for assignment 5.2.b
  }

  @Override
  public void rangeQuery(final List<T> resultList, final Rectangle query) {
    // TODO Insert code for assignment 5.2.c
  }
}
