package net.sertik.genesia.render;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import net.sertik.genesia.entity.GameObject;
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

    List<Node> nodes = new LinkedList<Node>();
    for (int i = 0; i < world.getSizeSqrt(); i++) {
      for (int j = 0; j < world.getSizeSqrt(); j++) {
        Tile tile = world.getTile(i, j);
        for (GameObject object : tile.getObjects()) {
          Node node = resourceLoader.createResource(object);
          node.setLayoutX(container.getTranslateX() + World.TILE_WIDTH / 2 * (i - j));
          node.setLayoutY(container.getTranslateY() + World.TILE_HEIGHT / 2 * (i + j));
          nodes.add(node);
        }
      }
    }

    container.getChildren().clear();
    container.getChildren().addAll(nodes);
  }
}
