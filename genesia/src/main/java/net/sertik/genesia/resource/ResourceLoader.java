package net.sertik.genesia.resource;

import javafx.scene.Node;
import net.sertik.genesia.entity.GameObject;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.ui.TileNode;

/**
 *
 * @author joeri
 */
public abstract class ResourceLoader {
  public abstract Node createResource(GameObject gameObject);

  public TileNode createResource(Tile tile) {
		if (! tile.getObjects().isEmpty()) {
			TileNode tileNode = new TileNode(tile);
			for (GameObject gameObject : tile.getObjects()) {
				tileNode.getChildren().add(createResource(gameObject));
			}
			return tileNode;
		}

		return null;
	}
}
