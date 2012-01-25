package net.sertik.genesia.render;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import net.sertik.genesia.entity.GameObject;
import net.sertik.genesia.entity.Scenery;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.resource.ResourceLoader;

/**
 *
 * @author Joeri
 */
public class SimpleRenderer implements Renderer {
  private ResourceLoader resourceLoader;
  private World world;

	private Node hoverTile;

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @Override
  public void setWorld(World world) {
    this.world = world;
  }

  @Override
  public void render(Group container, double width, double height) {
    if (resourceLoader == null) throw new RuntimeException("No ResourceLoader specified.");

		if (container.getChildren().isEmpty()) {
			List<Node> nodes = new LinkedList<>();
			for (int i = 0; i < world.getSizeSqrt(); i++) {
				for (int j = 0; j < world.getSizeSqrt(); j++) {
					Tile tile = world.getTile(i, j);
					for (GameObject object : tile.getObjects()) {
						Node node = resourceLoader.createResource(object);
						node.setLayoutX(World.TILE_WIDTH / 2 * (i - j));
						node.setLayoutY(World.TILE_HEIGHT / 2 * (i + j));
						nodes.add(node);
					}
				}
			}

			container.getChildren().addAll(nodes);
		}

		// render hover tile
		if (world.getHoverWorldX() != -1 && world.getHoverWorldY() != -1) {
			if (hoverTile == null) {
				hoverTile = resourceLoader.createResource(Scenery.HOVER_TILE);
				hoverTile.setTranslateZ(1.0);
				container.getChildren().add(hoverTile);
			}
			hoverTile.setLayoutX(World.TILE_WIDTH / 2 * (world.getHoverWorldX() - world.getHoverWorldY()));
			hoverTile.setLayoutY(World.TILE_HEIGHT / 2 * (world.getHoverWorldX() + world.getHoverWorldY()));
		}
  }
}
