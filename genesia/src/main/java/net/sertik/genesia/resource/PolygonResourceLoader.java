package net.sertik.genesia.resource;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import net.sertik.genesia.entity.Construction;
import net.sertik.genesia.entity.GameObject;
import net.sertik.genesia.entity.Recruit;
import net.sertik.genesia.entity.Scenery;
import net.sertik.genesia.entity.World;

/**
 *
 * @author joeri
 */
public class PolygonResourceLoader implements ResourceLoader {
  @Override
  public Node createResource(GameObject gameObject) {
    Polygon polygon = new Polygon();
    polygon.setFill(getFill(gameObject));
    polygon.getPoints().addAll(
            World.TILE_WIDTH / 2.0, 0.0,
            World.TILE_WIDTH * 1.0, World.TILE_HEIGHT / 2.0,
            World.TILE_WIDTH / 2.0, World.TILE_HEIGHT * 1.0,
            0.0, World.TILE_HEIGHT / 2.0);
    return polygon;
  }

  private Paint getFill(GameObject object) {
    if (object instanceof Scenery) {
      if ("grass".equals(object.getName())) {
        return Color.LIGHTGREEN;
      } else if ("tree".equals(object.getName())) {
        return Color.DARKGREEN;
      } else {
        return Color.GREEN;
      }
    } else if (object instanceof Construction) {
      return Color.ORANGE;
    } else if (object instanceof Recruit) {
      return Color.GRAY;
    } else {
      return Color.BLUE;
    }
  }
}
