package net.sertik.genesia.render;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.resource.ResourceLoader;
import net.sertik.genesia.ui.TileNode;

/**
 *
 * @author Joeri
 */
public class SimpleRenderer extends Renderer {
	public SimpleRenderer(ResourceLoader resourceLoader, World world) {
		super(resourceLoader, world);
	}

  @Override
  public void renderTiles(Group container, double width, double height) {
		if (container.getChildren().isEmpty()) {
			List<Node> nodes = new LinkedList<>();
			for (int i = 0; i < world.getSizeSqrt(); i++) {
				for (int j = 0; j < world.getSizeSqrt(); j++) {
					Tile tile = world.getTile(i, j);
					TileNode node = resourceLoader.createResource(tile);
					if (node != null) {
						node.setLayoutX(World.TILE_WIDTH / 2 * (i - j));
						node.setLayoutY(World.TILE_HEIGHT / 2 * (i + j));
						nodes.add(node);
					}
				}
			}

			container.getChildren().addAll(nodes);
		}
  }
}
