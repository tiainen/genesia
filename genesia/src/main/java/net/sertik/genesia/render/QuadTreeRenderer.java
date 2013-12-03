package net.sertik.genesia.render;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.resource.ResourceLoader;
import net.sertik.genesia.ui.OrderedGroup;

/**
 *
 * @author Joeri
 */
public class QuadTreeRenderer extends Renderer {
  private int minTilesWidth = 4;

  private BoundingBox checkBounds;
  private List<Node> visibleNodesToAdd = new LinkedList<>();
  private List<Node> visibleNodesToRemove = new LinkedList<>();
  private Map<Point2D, Node> visibleNodes = new HashMap<>();

	public QuadTreeRenderer(ResourceLoader resourceLoader, World world) {
		super(resourceLoader, world);
	}

  @Override
  public void renderTiles(Group container, double width, double height) {
		// render map
    checkBounds = new BoundingBox(
            0 - container.getTranslateX() - World.TILE_WIDTH,
            0 - container.getTranslateY(),
            width + World.TILE_WIDTH, height + World.TILE_HEIGHT * 3);
    visibleNodesToAdd.clear();
    visibleNodesToRemove.clear();
    renderLevel(0 - world.getSizeSqrt() * World.TILE_WIDTH / 2, 0, 0, 0, world.getSizeSqrt());
    deleteRemainingNodes();
    container.getChildren().addAll(visibleNodesToAdd);
    container.getChildren().removeAll(visibleNodesToRemove);
    ((OrderedGroup) container).sort();
  }

  private void renderLevel(double parentMinX, double parentMinY,
          int parentOffsetX, int parentOffsetY, int width) {
    if (width > minTilesWidth && width % 2 == 0) {
      int newWidth = width / 2;
      for (int i = 0; i < 4; i++) {
        int x = i / 2;
        int y = i % 2;
        BoundingBox bounds = new BoundingBox(
                parentMinX + (newWidth * World.TILE_WIDTH) / 2 + (newWidth * World.TILE_WIDTH) / 2 * (x - y),
                parentMinY + (newWidth * World.TILE_HEIGHT) / 2 * (x + y),
                newWidth * World.TILE_WIDTH,
                newWidth * World.TILE_HEIGHT);
        if (bounds.intersects(checkBounds)) {
          renderLevel(bounds.getMinX(), bounds.getMinY(),
                  parentOffsetX + x * newWidth, parentOffsetY + y * newWidth,
                  newWidth);
        }
      }
    } else {
      int to = (int) Math.pow(width, 2);
      for (int i = 0; i < to; i++) {
        int x = i / width;
        int y = i % width;
        BoundingBox bounds = new BoundingBox(
                parentMinX + (width * World.TILE_WIDTH) / 2 + World.TILE_WIDTH / 2 * (x - y),
                parentMinY + World.TILE_HEIGHT / 2 * (x + y),
                World.TILE_WIDTH, World.TILE_HEIGHT);
        Point2D point = new Point2D(bounds.getMinX(), bounds.getMinY());
        if (bounds.intersects(checkBounds)) {
          if (! visibleNodes.containsKey(point)) {
            Tile tile = world.getTile(parentOffsetX + x, parentOffsetY + y);
						Node node = resourceLoader.createResource(tile);
						if (node != null) {
							node.setLayoutX(point.getX());
							node.setLayoutY(point.getY());
							visibleNodes.put(point, node);
							visibleNodesToAdd.add(node);
						}
          }
        } else {
          visibleNodesToRemove.add(visibleNodes.get(point));
          visibleNodes.remove(point);
        }
      }
    }
  }

  private void deleteRemainingNodes() {
    List<Point2D> visiblePointsToRemove = new LinkedList<>();
    for (Point2D point : visibleNodes.keySet()) {
      BoundingBox bounds = new BoundingBox(
              point.getX(), point.getY(),
              World.TILE_WIDTH, visibleNodes.get(point).getLayoutBounds().getHeight());
      if (! bounds.intersects(checkBounds)) {
        visibleNodesToRemove.add(visibleNodes.get(point));
        visiblePointsToRemove.add(point);
      }
    }

    visibleNodes.keySet().removeAll(visiblePointsToRemove);
  }
}
