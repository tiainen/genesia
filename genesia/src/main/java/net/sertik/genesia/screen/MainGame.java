package net.sertik.genesia.screen;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import net.sertik.genesia.Genesia;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.media.Assets;
import net.sertik.genesia.render.Renderer;
import net.sertik.genesia.ui.OrderedGroup;
import net.sertik.genesia.ui.TileWithDescriptionNode;
import net.sertik.genesia.util.Coords;

/**
 *
 * @author joeri
 */
public class MainGame extends Group {

	private final Renderer renderer;

	private final double screenWidth;
	private final double screenHeight;

	private Rectangle worldScrollBoundaries = new Rectangle();

	private OrderedGroup tilesGroup;

	private TileWithDescriptionNode selectedTileInfo;

	private double dragStartX = 0;
	private double dragStartY = 0;

	private static final int MOUSEMAP_CENTER = 0;
	private static final int MOUSEMAP_NW = 1;
	private static final int MOUSEMAP_NE = 2;
	private static final int MOUSEMAP_SW = 3;
	private static final int MOUSEMAP_SE = 4;
	private int mouseMapLookupTable[][] = new int[World.TILE_WIDTH][World.TILE_HEIGHT];

	public MainGame(final Genesia genesia, final Renderer renderer, double screenWidth, double screenHeight) {
		// initialize top half
		for (int j = 0; j < World.TILE_HEIGHT / 2; j++) {
			for (int i = 0; i < World.TILE_WIDTH; i++) {
				int ri = World.TILE_WIDTH / 2;
				if (i < ri - (j * 2) - 1) {
					mouseMapLookupTable[i][j] = MOUSEMAP_NW;
				}
				if (i > ri + j * 2) {
					mouseMapLookupTable[i][j] = MOUSEMAP_NE;
				}
			}
		}
		// initialize bottom half
		for (int j = World.TILE_HEIGHT / 2; j > 0; j--) {
			for (int i = 0; i < World.TILE_WIDTH; i++) {
				int ri = World.TILE_WIDTH / 2;
				if (i < ri - (j * 2) + 1) {
					mouseMapLookupTable[i][World.TILE_HEIGHT - j] = MOUSEMAP_SW;
				}
				if (i > ri + (j * 2) - 2) {
					mouseMapLookupTable[i][World.TILE_HEIGHT - j] = MOUSEMAP_SE;
				}
			}
		}

		this.renderer = renderer;

		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		Rectangle inputCapture = new Rectangle();
		inputCapture.setFill(Color.TRANSPARENT);
		inputCapture.setFocusTraversable(true);
		inputCapture.requestFocus();

		// capture keyboard events
		inputCapture.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.ESCAPE) {
					genesia.setGame(null);
				} else {

				}
			}
		});

		// when the mouse is moved, update the hovering tile accordingly
		inputCapture.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				Coords mapCoords = calcMapCoordFromMouseCoord(me.getX(), me.getY());
				if (renderer.getWorld().setHoverCoords(mapCoords)) {
					render();
				}
			}
		});

		// when the mouse is clicked, update the selected tile if there was a tile
		inputCapture.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.isStillSincePress()) {
					Coords mapCoords = calcMapCoordFromMouseCoord(me.getX(), me.getY());
					if (renderer.getWorld().isPointWithinBounds(mapCoords)) {
						Tile selectedTile = renderer.getWorld().getTile(mapCoords.getX(), mapCoords.getY());
						if (selectedTileInfo.getTileNode() == null
										|| !selectedTile.equals(selectedTileInfo.getTileNode().getTile())) {
							selectedTileInfo.setTileNode(renderer.getResourceLoader().createResource(selectedTile));
						}
					}
				}
			}
		});

		// when the mouse is pressed down, keep track of the mouse position
		inputCapture.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				dragStartX = me.getSceneX();
				dragStartY = me.getSceneY();
			}
		});

		// when the mouse is dragged, use it to update the position of the map
		inputCapture.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (!me.isStillSincePress()) {
					double diffX = me.getSceneX() - dragStartX;
					double diffY = me.getSceneY() - dragStartY;
					if (diffX != 0 || diffY != 0) {
						// bound x and y position within map bounds
						double newXPosition = tilesGroup.getTranslateX() + diffX;
						if (newXPosition < worldScrollBoundaries.getWidth()) {
							newXPosition = worldScrollBoundaries.getWidth();
						} else if (newXPosition > worldScrollBoundaries.getX()) {
							newXPosition = worldScrollBoundaries.getX();
						}

						double newYPosition = tilesGroup.getTranslateY() + diffY;
						double yUpperBound = World.TILE_HEIGHT * 3 - (Math.abs((newXPosition - (screenWidth - 250) / 2) / 2));
						double yLowerBound = (World.TILE_HEIGHT * (renderer.getWorld().getSizeSqrt() + 2) * -1) + screenHeight + (Math.abs((newXPosition - (screenWidth - 250) / 2) / 2));
						if (yUpperBound > yLowerBound) {
							if (newYPosition > yUpperBound) {
								newYPosition = yUpperBound;
							} else if (newYPosition < yLowerBound) {
								newYPosition = yLowerBound;
							}
						} else {
							newYPosition = tilesGroup.getTranslateY();
						}

						if (newXPosition != tilesGroup.getTranslateX() || newYPosition != tilesGroup.getTranslateY()) {
							tilesGroup.setTranslateX(newXPosition);
							tilesGroup.setTranslateY(newYPosition);

							render();
						}

						dragStartX = me.getSceneX();
						dragStartY = me.getSceneY();
					}
				}
			}
		});

		final ImageView mainBackground = new ImageView();
		mainBackground.setImage(Assets.getBackground(Assets.IMAGE_SCREENS_GAME_MAIN_BACKGROUND));

		final ImageView menuBackground = new ImageView();
		menuBackground.setImage(Assets.getBackground(Assets.IMAGE_SCREENS_MENU_BACKGROUND));

		selectedTileInfo = new TileWithDescriptionNode();
		selectedTileInfo.setLayoutX(61.0);
		selectedTileInfo.setLayoutY(36.0);

		Rectangle selectedTileInfoRect = new Rectangle(160.0, 176.0, Color.WHITE);
		selectedTileInfoRect.setArcWidth(3.0);
		selectedTileInfoRect.setArcHeight(3.0);
		selectedTileInfoRect.setOpacity(0.5);

		StackPane selectedTileInfoGroup = new StackPane();
		selectedTileInfoGroup.setPrefWidth(160.0);
		selectedTileInfoGroup.getChildren().addAll(selectedTileInfoRect, selectedTileInfo);
		selectedTileInfoGroup.setLayoutX((250.0 - selectedTileInfoGroup.getPrefWidth()) / 2.0);
		selectedTileInfoGroup.setLayoutY(25.0);

		Group menu = new Group();
		menu.setLayoutX(screenWidth - 250);
		menu.getChildren().addAll(menuBackground, selectedTileInfoGroup);

		tilesGroup = new OrderedGroup();
		tilesGroup.setTranslateX((screenWidth - 250) / 2);
		tilesGroup.setTranslateY(World.TILE_HEIGHT * 3);

		worldScrollBoundaries.setX(World.TILE_WIDTH * renderer.getWorld().getSizeSqrt() / 2.0);
		worldScrollBoundaries.setWidth(World.TILE_WIDTH * (renderer.getWorld().getSizeSqrt() + 2.0) / 2.0 * -1.0 + (screenWidth - 250.0));

		// clipping container for rendered land
		Group clipContainer = new Group();
		clipContainer.setClip(new Rectangle(screenWidth - 250, screenHeight));
		clipContainer.getChildren().add(tilesGroup);

		// land can only be dragged by the size of the container
		inputCapture.setWidth(clipContainer.getClip().getLayoutBounds().getWidth());
		inputCapture.setHeight(clipContainer.getClip().getLayoutBounds().getHeight());

		getChildren().addAll(mainBackground, menu, clipContainer, inputCapture);
	}

	private Coords calcMapCoordFromMouseCoord(double mouseX, double mouseY) {
		double pickX = mouseX - tilesGroup.getTranslateX();
		double pickY = mouseY - tilesGroup.getTranslateY();

		int coarseMouseMapX = (int) (pickX / World.TILE_WIDTH);
		int coarseMouseMapY = (int) (pickY / World.TILE_HEIGHT);
		int fineMouseMapX = (int) (pickX % World.TILE_WIDTH);
		int fineMouseMapY = (int) (pickY % World.TILE_HEIGHT);
		if (fineMouseMapX < 0) {
			fineMouseMapX += World.TILE_WIDTH;
			coarseMouseMapX--;
		}
		if (fineMouseMapY < 0) {
			fineMouseMapY += World.TILE_HEIGHT;
			coarseMouseMapY--;
		}

		int mapX = coarseMouseMapX + coarseMouseMapY;
		int mapY = -coarseMouseMapX + coarseMouseMapY;

		// take fine mouse map coords into account by using the lookup
		// table to define the final map coords
		switch (mouseMapLookupTable[fineMouseMapX][fineMouseMapY]) {
			case MOUSEMAP_NW:
				mapX--;
				break;
			case MOUSEMAP_NE:
				mapY--;
				break;
			case MOUSEMAP_SW:
				mapY++;
				break;
			case MOUSEMAP_SE:
				mapX++;
				break;
			case MOUSEMAP_CENTER:
				break;
		}

		return new Coords(mapX, mapY);
	}

	public void render() {
		renderer.render(tilesGroup, screenWidth, screenHeight);
	}
}
