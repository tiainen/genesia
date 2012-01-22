package net.sertik.genesia.screen;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import net.sertik.genesia.Genesia;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.render.Renderer;
import net.sertik.genesia.ui.OrderedGroup;

/**
 *
 * @author joeri
 */
public class MainGame extends Group {
  private double width;
  private double height;

  private Renderer renderer;

  private OrderedGroup tilesGroup;
	private Group grid = new Group();

  private double dragStartX = 0;
  private double dragStartY = 0;

	private static final int MOUSEMAP_CENTER = 0;
	private static final int MOUSEMAP_NW = 1;
	private static final int MOUSEMAP_NE = 2;
	private static final int MOUSEMAP_SW = 3;
	private static final int MOUSEMAP_SE = 4;
	private int mouseMapLookupTable[][];

  public MainGame(final Genesia genesia, double width, double height) {
		// TODO: properly initialize lookup table for mouse map
		mouseMapLookupTable = new int[World.TILE_WIDTH][World.TILE_HEIGHT];
		for (int i = 0; i < World.TILE_WIDTH; i++) {
			for (int j = 0; j < World.TILE_HEIGHT; j++) {
				mouseMapLookupTable[i][j] = MOUSEMAP_CENTER;
			}
		}

    this.width = width;
    this.height = height;

    Rectangle inputCapture = new Rectangle();
    inputCapture.setWidth(width);
    inputCapture.setHeight(height);
    inputCapture.setFill(Color.TRANSPARENT);
		inputCapture.setFocusTraversable(true);
		inputCapture.requestFocus();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				grid.getChildren().add(RectangleBuilder.create().fill(i % 2 == 0 ? (j % 2 == 0 ? Color.BLUE : Color.BLACK) : (j % 2 == 1 ? Color.GRAY : Color.ORANGE)).opacity(0.2).width(World.TILE_WIDTH).height(World.TILE_HEIGHT).layoutX(i * World.TILE_WIDTH).layoutY(j * World.TILE_HEIGHT).build());
			}
		}

    inputCapture.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent ke) {
        if (ke.getCode() == KeyCode.ESCAPE) {
          genesia.setGame(null);
        } else {
          
        }
      }
    });
		inputCapture.setOnMouseMoved(new EventHandler<MouseEvent>() {
      @Override
			public void handle(MouseEvent me) {
        double pickX = me.getX() - tilesGroup.getTranslateX();
        double pickY = me.getY() - tilesGroup.getTranslateY();

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

				genesia.getGame().getWorld().setActiveTile(mapX, mapY);
			}
		});
		inputCapture.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        dragStartX = me.getSceneX();
        dragStartY = me.getSceneY();
      }
    });
    inputCapture.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
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
    });

    tilesGroup = new OrderedGroup();
		tilesGroup.setTranslateX(width / 2);
		grid.translateXProperty().bind(tilesGroup.translateXProperty());
		grid.translateYProperty().bind(tilesGroup.translateYProperty());
    getChildren().addAll(tilesGroup, grid, inputCapture);
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
