package net.sertik.genesia.resource;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import net.sertik.genesia.entity.GameObject;
import net.sertik.genesia.entity.Scenery;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.media.Assets;

/**
 *
 * @author joeri
 */
public class ImageResourceLoader extends ResourceLoader {
  @Override
  public Node createResource(GameObject object) {
		if (object.equals(Scenery.HOVER_TILE)) {
			Polygon polygon = new Polygon();
			polygon.getPoints().addAll(
							World.TILE_WIDTH / 2.0, 0.0,
							World.TILE_WIDTH * 1.0, World.TILE_HEIGHT / 2.0,
							World.TILE_WIDTH / 2.0, World.TILE_HEIGHT * 1.0,
							0.0, World.TILE_HEIGHT / 2.0);
			polygon.setStrokeType(StrokeType.INSIDE);
			polygon.getStrokeDashArray().addAll(4.0, 8.0);
			polygon.setStroke(Color.ORANGE);
			polygon.setStrokeWidth(2.0);
			polygon.setFill(Color.TRANSPARENT);
			return polygon;
		} else {
			Image image = Assets.getImages().get(Assets.IMAGE_TILES_SPRING_GRASS_01);
			ImageView imageView = new ImageView();
			imageView.setImage(image);
		  imageView.setTranslateY(-image.getHeight() + World.TILE_HEIGHT);
	    return imageView;
		}
  }
}
