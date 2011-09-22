package net.sertik.genesia.screen;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import net.sertik.genesia.Genesia;
import net.sertik.genesia.entity.Game;
import net.sertik.genesia.entity.Player;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.world.FlatLandWorldGenerator;
import net.sertik.genesia.media.Assets;

/**
 *
 * @author joeri
 */
public class GameSelection extends StackPane {
  private final ToggleGroup numberOfPlayers;
  private final ToggleGroup worldCreation;
  private final ToggleGroup gameLevel;

  public GameSelection(final Genesia genesia) {
    setAlignment(Pos.CENTER_LEFT);

    Rectangle backgroundRect = new Rectangle();
    backgroundRect.setFill(Color.BLACK);
    backgroundRect.setWidth(640);
    backgroundRect.setHeight(400);

    final FadeTransition fadeInSelection = new FadeTransition(Duration.millis(500));
    final FadeTransition fadeOutBackground = new FadeTransition(Duration.millis(1000));

    final GridPane outerPane = new GridPane();
    outerPane.setVisible(false);
    outerPane.setOpacity(0.0);
    outerPane.setPadding(new Insets(0, 15, 0, 15));
    outerPane.setVgap(10);

    final ImageView background = new ImageView();
    background.setCursor(Cursor.HAND);
    background.setOpacity(1.0);
    background.setImage(Assets.getImages().get(Assets.IMAGE_SCREENS_GAME_SELECTION_BACKGROUND));
    background.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        if (! outerPane.isVisible()) {
          background.setCursor(null);
          outerPane.setVisible(true);
          fadeInSelection.play();
          fadeOutBackground.play();
        }
      }
    });

    fadeInSelection.setCycleCount(1);
    fadeInSelection.setFromValue(1.0);
    fadeInSelection.setToValue(0.3);
    fadeInSelection.setNode(background);
    fadeOutBackground.setCycleCount(1);
    fadeOutBackground.setFromValue(0.0);
    fadeOutBackground.setToValue(1.0);
    fadeOutBackground.setNode(outerPane);

    Label numberOfPlayersLabel = new Label("NUMBER OF PLAYERS");
    numberOfPlayersLabel.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_16));
    numberOfPlayersLabel.setTextFill(Color.LIGHTGRAY);
    GridPane.setConstraints(numberOfPlayersLabel, 0, 0);
    GridPane.setColumnSpan(numberOfPlayersLabel, 5);

    numberOfPlayers = new ToggleGroup();

    RadioButton numberOfPlayers1 = new RadioButton("1");
    numberOfPlayers1.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    numberOfPlayers1.setSelected(true);
    numberOfPlayers1.setUserData(1);
    numberOfPlayers1.setToggleGroup(numberOfPlayers);
    GridPane.setConstraints(numberOfPlayers1, 0, 1);
    numberOfPlayers1.setMinWidth(120);
    numberOfPlayers1.setMaxWidth(120);

    RadioButton numberOfPlayers2 = new RadioButton("2");
    numberOfPlayers2.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    numberOfPlayers2.setUserData(2);
    numberOfPlayers2.setToggleGroup(numberOfPlayers);
    GridPane.setConstraints(numberOfPlayers2, 1, 1);
    numberOfPlayers2.setMinWidth(120);
    numberOfPlayers2.setMaxWidth(120);

    RadioButton numberOfPlayers3 = new RadioButton("3");
    numberOfPlayers3.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    numberOfPlayers3.setUserData(3);
    numberOfPlayers3.setToggleGroup(numberOfPlayers);
    GridPane.setConstraints(numberOfPlayers3, 2, 1);
    numberOfPlayers3.setMinWidth(120);
    numberOfPlayers3.setMaxWidth(120);

    Line horizontalLine1 = new Line();
    horizontalLine1.setTranslateX(30);
    horizontalLine1.setEndX(550);
    horizontalLine1.setStroke(Color.GRAY);
    horizontalLine1.setStrokeWidth(3);
    GridPane.setConstraints(horizontalLine1, 0, 2);
    GridPane.setColumnSpan(horizontalLine1, 5);
    GridPane.setMargin(horizontalLine1, new Insets(15, 0, 15, 0));

    Label worldCreationLabel = new Label("CREATION OF THE WORLD");
    worldCreationLabel.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_16));
    worldCreationLabel.setTextFill(Color.LIGHTGRAY);
    GridPane.setConstraints(worldCreationLabel, 0, 3);
    GridPane.setColumnSpan(worldCreationLabel, 5);

    worldCreation = new ToggleGroup();

    RadioButton worldCreation1 = new RadioButton("1");
    worldCreation1.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation1.setSelected(true);
    worldCreation1.setUserData(1);
    worldCreation1.setToggleGroup(worldCreation);
    worldCreation1.setMinWidth(120);
    worldCreation1.setMaxWidth(120);
    GridPane.setConstraints(worldCreation1, 0, 4);

    RadioButton worldCreation2 = new RadioButton("2");
    worldCreation2.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation2.setUserData(2);
    worldCreation2.setToggleGroup(worldCreation);
    worldCreation2.setMinWidth(120);
    worldCreation2.setMaxWidth(120);
    GridPane.setConstraints(worldCreation2, 1, 4);

    RadioButton worldCreation3 = new RadioButton("3");
    worldCreation3.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation3.setUserData(3);
    worldCreation3.setToggleGroup(worldCreation);
    worldCreation3.setMinWidth(120);
    worldCreation3.setMaxWidth(120);
    GridPane.setConstraints(worldCreation3, 2, 4);

    RadioButton worldCreation4 = new RadioButton("4");
    worldCreation4.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation4.setUserData(4);
    worldCreation4.setToggleGroup(worldCreation);
    worldCreation4.setMinWidth(120);
    worldCreation4.setMaxWidth(120);
    GridPane.setConstraints(worldCreation4, 3, 4);

    RadioButton worldCreation5 = new RadioButton("5");
    worldCreation5.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation5.setUserData(5);
    worldCreation5.setToggleGroup(worldCreation);
    worldCreation5.setMinWidth(120);
    worldCreation5.setMaxWidth(120);
    GridPane.setConstraints(worldCreation5, 4, 4);

    Line horizontalLine2 = new Line();
    horizontalLine2.setTranslateX(30);
    horizontalLine2.setEndX(550);
    horizontalLine2.setStroke(Color.GRAY);
    horizontalLine2.setStrokeWidth(3);
    GridPane.setConstraints(horizontalLine2, 0, 5);
    GridPane.setColumnSpan(horizontalLine2, 5);
    GridPane.setMargin(horizontalLine2, new Insets(15, 0, 15, 0));

    Label gameLevelLabel = new Label("GAME LEVEL");
    gameLevelLabel.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_16));
    gameLevelLabel.setTextFill(Color.LIGHTGRAY);
    GridPane.setConstraints(gameLevelLabel, 0, 6);
    GridPane.setColumnSpan(gameLevelLabel, 5);

    gameLevel = new ToggleGroup();

    RadioButton gameLevel1 = new RadioButton("BEGINNER");
    gameLevel1.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    gameLevel1.setUserData(Game.Difficulty.BEGINNER);
    gameLevel1.setToggleGroup(gameLevel);
    gameLevel1.setMinWidth(120);
    gameLevel1.setMaxWidth(120);
    GridPane.setConstraints(gameLevel1, 0, 7);

    RadioButton gameLevel2 = new RadioButton("NORMAL");
    gameLevel2.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    gameLevel2.setSelected(true);
    gameLevel2.setUserData(Game.Difficulty.NORMAL);
    gameLevel2.setToggleGroup(gameLevel);
    gameLevel2.setMinWidth(120);
    gameLevel2.setMaxWidth(120);
    GridPane.setConstraints(gameLevel2, 1, 7);

    RadioButton gameLevel3 = new RadioButton("ADVANCED");
    gameLevel3.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    gameLevel3.setUserData(Game.Difficulty.ADVANCED);
    gameLevel3.setToggleGroup(gameLevel);
    gameLevel3.setMinWidth(120);
    gameLevel3.setMaxWidth(120);
    GridPane.setConstraints(gameLevel3, 2, 7);

    Button startNewGame = new Button("NEW GAME");
    startNewGame.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    startNewGame.setCursor(Cursor.HAND);
    startNewGame.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent ae) {
        Game game = new Game();
        game.setDifficulty((Game.Difficulty) gameLevel.getSelectedToggle().getUserData());

        int totalPlayers = (Integer) numberOfPlayers.getSelectedToggle().getUserData();
        for (int i = 0; i < totalPlayers; i++) {
          Player player = new Player();
          player.setComputer(false);
          player.setColor(Player.COLORS[i]);
          game.getPlayers().add(player);
        }
        for (int i = totalPlayers; i < 3; i++) {
          Player player = new Player();
          player.setComputer(true);
          player.setColor(Player.COLORS[i]);
          player.setName(Player.NAMES[i - totalPlayers]);
          game.getPlayers().add(player);
        }

        World world = new FlatLandWorldGenerator(0.25).generate(1, 256);
//        int world = (Integer) worldCreation.getSelectedToggle().getUserData();
        game.setWorld(world);

        genesia.setGame(game);
      }
    });
    GridPane.setConstraints(startNewGame, 0, 8);
    GridPane.setMargin(startNewGame, new Insets(25, 0, 0, 0));

    outerPane.getChildren().addAll(numberOfPlayersLabel,
            numberOfPlayers1, numberOfPlayers2, numberOfPlayers3,
            horizontalLine1,
            worldCreationLabel, worldCreation1, worldCreation2,
            worldCreation3, worldCreation4, worldCreation5,
            horizontalLine2,
            gameLevelLabel, gameLevel1, gameLevel2, gameLevel3,
            startNewGame);

    getChildren().addAll(backgroundRect, background, outerPane);
  }
}
