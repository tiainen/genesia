package net.sertik.genesia.resource;

import javafx.scene.Node;
import net.sertik.genesia.entity.GameObject;

/**
 *
 * @author joeri
 */
public interface ResourceLoader {
  public Node createResource(GameObject object);
}
