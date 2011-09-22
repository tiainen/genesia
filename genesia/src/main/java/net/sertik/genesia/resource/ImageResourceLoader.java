package net.sertik.genesia.resource;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sertik.genesia.entity.GameObject;
import net.sertik.genesia.media.Assets;

/**
 *
 * @author joeri
 */
public class ImageResourceLoader implements ResourceLoader {
  @Override
  public Node createResource(GameObject object) {
    Image image = Assets.getImages().get(Assets.IMAGE_TILES_SPRING_GRASS_01);
    ImageView iv = new ImageView();
    iv.setImage(image);
    iv.setTranslateY(-image.getHeight());
    return iv;
  }
}
