package net.sertik.genesia.resource;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import net.sertik.genesia.entity.Construction;
import net.sertik.genesia.entity.GameObject;
import net.sertik.genesia.entity.Recruit;
import net.sertik.genesia.entity.Scenery;
import net.sertik.genesia.entity.World;

/**
 *
 * @author joeri
 */
public class PolygonResourceLoader extends ResourceLoader {
	public PolygonResourceLoader(World world) {
		super(world);
	}

	@Override
  public Node createResource(GameObject gameObject) {
    Polygon polygon = new Polygon();
    polygon.getPoints().addAll(
            World.TILE_WIDTH / 2.0, 0.0,
            World.TILE_WIDTH * 1.0, World.TILE_HEIGHT / 2.0,
            World.TILE_WIDTH / 2.0, World.TILE_HEIGHT * 1.0,
            0.0, World.TILE_HEIGHT / 2.0);
		if (gameObject.equals(Scenery.HOVER_TILE)) {
			polygon.setStrokeType(StrokeType.INSIDE);
			polygon.getStrokeDashArray().addAll(4.0, 8.0);
			polygon.setStroke(Color.ORANGE);
			polygon.setStrokeWidth(2.0);
			polygon.setFill(Color.TRANSPARENT);
		} else {
	    polygon.setFill(getFill(gameObject));
		}
    return polygon;
  }

  private Paint getFill(GameObject object) {
    if (object instanceof Scenery) {
			switch (object.getName()) {
				case "grass":
					return Color.LIGHTGREEN;
				case "tree":
					return Color.DARKGREEN;
				default:
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
