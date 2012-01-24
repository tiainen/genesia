package net.sertik.genesia.ui;

import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 *
 * @author joeri
 */
public class OrderedGroup extends Group {
  private Comparator<Node> comparator = new Comparator<Node>() {
    @Override
    public int compare(Node n1, Node n2) {
			if (n1.getTranslateZ() == n2.getTranslateZ()) {
				if (n1.getLayoutY() == n2.getLayoutY()) {
					return (int) (n1.getLayoutX() - n2.getLayoutX());
				} else {
					return (int) (n1.getLayoutY() - n2.getLayoutY());
				}
			} else {
				return (int) (n1.getTranslateZ() - n2.getTranslateZ());
			}
    }
  };

  public void sort() {
    FXCollections.sort(getChildren(), comparator);
  }
}
