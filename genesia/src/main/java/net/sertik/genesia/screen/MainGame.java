package net.sertik.genesia.screen;

import java.awt.Point;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import net.sertik.genesia.Genesia;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.media.Assets;
import net.sertik.genesia.render.Renderer;
import net.sertik.genesia.ui.OrderedGroup;
import net.sertik.genesia.ui.TileWithDescriptionNode;

/**
 *
 * @author joeri
 */
public class MainGame extends Group {
  private double width;
  private double height;

  private Renderer renderer;

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

  public MainGame(final Genesia genesia, double width, double height) {
		// initialize top half
		for (int j = 0; j < World.TILE_HEIGHT / 2; j++) {
			for (int i = 0; i < World.TILE_WIDTH; i++) {
				int ri = World.TILE_WIDTH / 2;
				if (i < ri - (j * 2) - 1) mouseMapLookupTable[i][j] = 1;
				if (i > ri + j * 2) mouseMapLookupTable[i][j] = 2;
			}
		}
		// initialize bottom half
		for (int j = World.TILE_HEIGHT / 2; j > 0; j--) {
			for (int i = 0; i < World.TILE_WIDTH; i++) {
				int ri = World.TILE_WIDTH / 2;
				if (i < ri - (j * 2) + 1) mouseMapLookupTable[i][World.TILE_HEIGHT - j] = 3;
				if (i > ri + (j * 2) - 2) mouseMapLookupTable[i][World.TILE_HEIGHT - j] = 4;
			}
		}

    this.width = width;
    this.height = height;

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
				Point mapCoords = calcMapCoordFromMouseCoord(me.getX(), me.getY());
				if (genesia.getGame().getWorld().setHoverCoords(mapCoords.x, mapCoords.y)) {
					render();
				}
			}
		});

		// when the mouse is clicked, update the selected tile if there was a tile
		inputCapture.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.isStillSincePress()) {
					Point mapCoords = calcMapCoordFromMouseCoord(me.getX(), me.getY());
					if (genesia.getGame().getWorld().isPointWithinBounds(mapCoords.x, mapCoords.y)) {
						Tile selectedTile = genesia.getGame().getWorld().getTile(mapCoords.x, mapCoords.y);
						if (selectedTileInfo.getTileNode() == null ||
										! selectedTile.equals(selectedTileInfo.getTileNode().getTile())) {
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
				if (! me.isStillSincePress()) {
					double diffX = me.getSceneX() - dragStartX;
					double diffY = me.getSceneY() - dragStartY;
					if (diffX != 0 || diffY != 0) {
						tilesGroup.setTranslateX(tilesGroup.getTranslateX() + diffX);
						tilesGroup.setTranslateY(tilesGroup.getTranslateY() + diffY);
						render();
						dragStartX = me.getSceneX();
						dragStartY = me.getSceneY();
					}
				}
      }
    });

    final ImageView menuBackground = new ImageView();
    menuBackground.setOpacity(0.95);
    menuBackground.setImage(Assets.getImages().get(Assets.IMAGE_SCREENS_MENU_BACKGROUND));

		selectedTileInfo = new TileWithDescriptionNode();
		selectedTileInfo.setLayoutX(61.0);
		selectedTileInfo.setLayoutY(36.0);

		Rectangle selectedTileInfoRect = RectangleBuilder.create()
						.width(World.TILE_WIDTH + 50.0).height(World.TILE_HEIGHT * 3.0)
						.arcWidth(3.0).arcHeight(3.0)
						.fill(Color.WHITE)
						.opacity(0.5).build();

		StackPane selectedTileInfoGroup = new StackPane();
		selectedTileInfoGroup.setPrefWidth(World.TILE_WIDTH + 50.0);
		selectedTileInfoGroup.getChildren().addAll(selectedTileInfoRect, selectedTileInfo);
		selectedTileInfoGroup.setLayoutX((250.0 - selectedTileInfoGroup.getPrefWidth()) / 2.0);
		selectedTileInfoGroup.setLayoutY(25.0);

		Group menu = new Group();
		menu.setLayoutX(width - 250);
		menu.getChildren().addAll(menuBackground, selectedTileInfoGroup);

    tilesGroup = new OrderedGroup();
		tilesGroup.setTranslateX(width / 2);

		// clipping container for rendered land
		Group clipContainer = new Group();
		clipContainer.setClip(new Rectangle(width - 250, height));
		clipContainer.getChildren().add(tilesGroup);

		// land can only be dragged by the size of the container
		inputCapture.setWidth(clipContainer.getClip().getLayoutBounds().getWidth());
    inputCapture.setHeight(clipContainer.getClip().getLayoutBounds().getHeight());

		getChildren().addAll(menu, clipContainer, inputCapture);
  }

	private Point calcMapCoordFromMouseCoord(double mouseX, double mouseY) {
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
			case MOUSEMAP_NW: mapX--; break;
			case MOUSEMAP_NE: mapY--; break;
			case MOUSEMAP_SW: mapY++; break;
			case MOUSEMAP_SE: mapX++; break;
			case MOUSEMAP_CENTER: break;
		}

		return new Point(mapX, mapY);
	}

  public void setRenderer(Renderer renderer) {
    this.renderer = renderer;
  }

  public Renderer getRenderer() {
    return renderer;
  }

  public void render() {
    if (renderer == null) throw new RuntimeException("No Renderer specified.");

    renderer.render(tilesGroup, width, height);
  }
}
