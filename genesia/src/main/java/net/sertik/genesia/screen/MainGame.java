package net.sertik.genesia.screen;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import net.sertik.genesia.Genesia;
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

  private double dragStartX = 0;
  private double dragStartY = 0;

  public MainGame(final Genesia genesia, double width, double height) {
    this.width = width;
    this.height = height;

    Rectangle inputCapture = new Rectangle();
    inputCapture.setWidth(width);
    inputCapture.setHeight(height);
    inputCapture.setFill(Color.TRANSPARENT);

    inputCapture.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent ke) {
        System.out.println("BLEH: KeyPressed! " + ke.getCode() + ", " + KeyCode.ESCAPE);
        if (ke.getCode() == KeyCode.ESCAPE) {
          genesia.setGame(null);
        } else {
          
        }
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
    getChildren().addAll(tilesGroup, inputCapture);
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
