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
			if (n1.getUserData() == null && n2.getUserData() == null) {
				if (n1.getLayoutY() == n2.getLayoutY()) {
					return (int) (n1.getLayoutX() - n2.getLayoutX());
				} else {
					return (int) (n1.getLayoutY() - n2.getLayoutY());
				}
			} else {
				Integer udn1 = n1.getUserData() == null ? new Integer(0) : (Integer) n1.getUserData();
				Integer udn2 = n2.getUserData() == null ? new Integer(0) : (Integer) n2.getUserData();
				return udn1.compareTo(udn2);
			}
    }
  };

  public void sort() {
    FXCollections.sort(getChildren(), comparator);
  }
}
