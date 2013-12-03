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
		"tiles/spring/grass01.png",
		"tiles/spring/grass02.png",
		"tiles/spring/tree01.png",
		"tiles/spring/flag.png"
	};

	private static final String[] BACKGROUNDS = {
		"screens/gameSelectionBackground.png",
		"screens/menuBackground.png",
		"screens/gameMainBackground.jpg"
	};

	public static final int IMAGE_TILES_SPRING_BARRACKS = 0;
	public static final int IMAGE_TILES_SPRING_GRASS_01 = 1;
	public static final int IMAGE_TILES_SPRING_GRASS_02 = 2;
	public static final int IMAGE_TILES_SPRING_TREE_01 = 3;
	public static final int IMAGE_TILES_SPRING_FLAG = 4;

	public static final int IMAGE_SCREENS_GAME_SELECTION_BACKGROUND = 0;
	public static final int IMAGE_SCREENS_MENU_BACKGROUND = 1;
	public static final int IMAGE_SCREENS_GAME_MAIN_BACKGROUND = 2;

	private static List<Image> tiles = new LinkedList<>();

	public static Image getTile(int tile) {
		return tiles.get(tile);
	}

	private static List<Image> backgrounds = new LinkedList<>();

	public static Image getBackground(int background) {
		return backgrounds.get(background);
	}

	private static List<Font> fonts = new LinkedList<>();

	public static Font getFont(int font) {
		return fonts.get(font);
	}

	public static void initialize() {
		for (String imageResource : TILES) {
			Image image = new Image(Assets.class.getResourceAsStream(imageResource));
			if (image.isError()) {
				System.out.println("Image " + imageResource + " not found");
			}
			tiles.add(image);
		}

		for (String imageResource : BACKGROUNDS) {
			Image image = new Image(Assets.class.getResourceAsStream(imageResource));
			if (image.isError()) {
				System.out.println("Image " + imageResource + " not found");
			}
			backgrounds.add(image);
		}

		fonts.add(Font.font("Courier New", FontWeight.BOLD, 13));
		fonts.add(Font.font("Courier New", FontWeight.BOLD, 16));
	}

	private Assets() {
	}
}
