package net.sertik.genesia.media;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author joeri
 */
public class Assets {
  public static final int FONT_COURIERNEW_BOLD_13 = 0;
  public static final int FONT_COURIERNEW_BOLD_16 = 1;

  private static final String[] TILES = {
    "tiles/spring/barracks.png",
    "tiles/spring/grass01.png"
  };

  private static final String[] BACKGROUNDS = {
    "screens/gameSelectionBackground.png"
  };

  public static final int IMAGE_TILES_SPRING_BARRACKS = 0;
  public static final int IMAGE_TILES_SPRING_GRASS_01 = 1;

  public static final int IMAGE_SCREENS_GAME_SELECTION_BACKGROUND = 2;

  private static List<Image> images = new LinkedList<>();
  public static List<Image> getImages() {
    return images;
  }

  private static List<Font> fonts = new LinkedList<>();
  public static List<Font> getFonts() {
    return fonts;
  }

  public static void initialize() {
    for (String imageResource : TILES) {
      Image image = new Image(Assets.class.getResourceAsStream(imageResource));
      if (image.isError()) {
        System.out.println("Image " + imageResource + " not found");
      }
      images.add(image);
    }

    for (String imageResource : BACKGROUNDS) {
      Image image = new Image(Assets.class.getResourceAsStream(imageResource));
      if (image.isError()) {
        System.out.println("Image " + imageResource + " not found");
      }
      images.add(image);
    }

    System.out.println("images = " + images);

    fonts.add(Font.font("Courier New", FontWeight.BOLD, 13));
    fonts.add(Font.font("Courier New", FontWeight.BOLD, 16));
  }

  private Assets() {}
}
