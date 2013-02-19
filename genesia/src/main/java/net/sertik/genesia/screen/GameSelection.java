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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import net.sertik.genesia.Genesia;
import net.sertik.genesia.entity.Game;
import net.sertik.genesia.entity.Player;
import net.sertik.genesia.media.Assets;
import net.sertik.genesia.world.FlatLandWorldGenerator;
import net.sertik.genesia.world.WorldGenerator;

/**
 *
 * @author joeri
 */
public class GameSelection extends StackPane {
  private final ToggleGroup numberOfPlayers;
  private final ToggleGroup worldCreation;
  private final ToggleGroup gameLevel;

  public GameSelection(final Genesia genesia) {
    Rectangle backgroundRect = new Rectangle();
    backgroundRect.setFill(Color.BLACK);
    backgroundRect.setWidth(640);
    backgroundRect.setHeight(400);

    final FadeTransition fadeInSelection = new FadeTransition(Duration.millis(500));
    final FadeTransition fadeOutBackground = new FadeTransition(Duration.millis(1000));

    final VBox outerPane = new VBox();
    outerPane.setAlignment(Pos.CENTER);
    outerPane.setVisible(false);
    outerPane.setOpacity(0.0);
    outerPane.setPadding(new Insets(0, 15, 0, 15));
    outerPane.setSpacing(20);

    final ImageView background = new ImageView();
    background.setCursor(Cursor.HAND);
    background.setOpacity(1.0);
    background.setImage(Assets.getBackgrounds().get(Assets.IMAGE_SCREENS_GAME_SELECTION_BACKGROUND));
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

    numberOfPlayers = new ToggleGroup();

    RadioButton numberOfPlayers1 = new RadioButton("1");
    numberOfPlayers1.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    numberOfPlayers1.setSelected(true);
    numberOfPlayers1.setUserData(1);
    numberOfPlayers1.setToggleGroup(numberOfPlayers);
    numberOfPlayers1.setMinWidth(120);
    numberOfPlayers1.setMaxWidth(120);

    RadioButton numberOfPlayers2 = new RadioButton("2");
    numberOfPlayers2.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    numberOfPlayers2.setUserData(2);
    numberOfPlayers2.setToggleGroup(numberOfPlayers);
    numberOfPlayers2.setMinWidth(120);
    numberOfPlayers2.setMaxWidth(120);

    RadioButton numberOfPlayers3 = new RadioButton("3");
    numberOfPlayers3.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    numberOfPlayers3.setUserData(3);
    numberOfPlayers3.setToggleGroup(numberOfPlayers);

    HBox numberOfPlayersButtons = new HBox();
    numberOfPlayersButtons.setAlignment(Pos.CENTER);
    numberOfPlayersButtons.getChildren().addAll(numberOfPlayers1,
            numberOfPlayers2, numberOfPlayers3);

    Line horizontalLine1 = new Line();
    horizontalLine1.setEndX(480);
    horizontalLine1.setStroke(Color.GRAY);
    horizontalLine1.setStrokeWidth(2);

    Label worldCreationLabel = new Label("CREATION OF THE WORLD");
    worldCreationLabel.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_16));
    worldCreationLabel.setTextFill(Color.LIGHTGRAY);

    worldCreation = new ToggleGroup();

    RadioButton worldCreation1 = new RadioButton("1");
    worldCreation1.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation1.setSelected(true);
    worldCreation1.setUserData(new FlatLandWorldGenerator(0.15));
    worldCreation1.setToggleGroup(worldCreation);
    worldCreation1.setMinWidth(120);
    worldCreation1.setMaxWidth(120);

    RadioButton worldCreation2 = new RadioButton("2");
    worldCreation2.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation2.setUserData(new FlatLandWorldGenerator(0.3));
    worldCreation2.setToggleGroup(worldCreation);
    worldCreation2.setMinWidth(120);
    worldCreation2.setMaxWidth(120);

    RadioButton worldCreation3 = new RadioButton("3");
    worldCreation3.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation3.setUserData(new FlatLandWorldGenerator(0.45));
    worldCreation3.setToggleGroup(worldCreation);
    worldCreation3.setMinWidth(120);
    worldCreation3.setMaxWidth(120);

    RadioButton worldCreation4 = new RadioButton("4");
    worldCreation4.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation4.setUserData(new FlatLandWorldGenerator(0.6));
    worldCreation4.setToggleGroup(worldCreation);
    worldCreation4.setMinWidth(120);
    worldCreation4.setMaxWidth(120);

    RadioButton worldCreation5 = new RadioButton("5");
    worldCreation5.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    worldCreation5.setUserData(new FlatLandWorldGenerator(0.75));
    worldCreation5.setToggleGroup(worldCreation);

    HBox worldCreationButtons = new HBox();
    worldCreationButtons.setAlignment(Pos.CENTER);
    worldCreationButtons.getChildren().addAll(worldCreation1, worldCreation2,
            worldCreation3, worldCreation4, worldCreation5);
    
    Line horizontalLine2 = new Line();
    horizontalLine2.setEndX(480);
    horizontalLine2.setStroke(Color.GRAY);
    horizontalLine2.setStrokeWidth(2);

    Label gameLevelLabel = new Label("GAME LEVEL");
    gameLevelLabel.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_16));
    gameLevelLabel.setTextFill(Color.LIGHTGRAY);

    gameLevel = new ToggleGroup();

    RadioButton gameLevel1 = new RadioButton("BEGINNER");
    gameLevel1.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    gameLevel1.setUserData(Game.Difficulty.BEGINNER);
    gameLevel1.setToggleGroup(gameLevel);
    gameLevel1.setMinWidth(120);
    gameLevel1.setMaxWidth(120);

    RadioButton gameLevel2 = new RadioButton("NORMAL");
    gameLevel2.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    gameLevel2.setSelected(true);
    gameLevel2.setUserData(Game.Difficulty.NORMAL);
    gameLevel2.setToggleGroup(gameLevel);
    gameLevel2.setMinWidth(120);
    gameLevel2.setMaxWidth(120);

    RadioButton gameLevel3 = new RadioButton("ADVANCED");
    gameLevel3.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));
    gameLevel3.setUserData(Game.Difficulty.ADVANCED);
    gameLevel3.setToggleGroup(gameLevel);

    HBox gameLevelButtons = new HBox();
    gameLevelButtons.setAlignment(Pos.CENTER);
    gameLevelButtons.getChildren().addAll(gameLevel1, gameLevel2, gameLevel3);
    
    Button startNewGame = new Button("NEW GAME");
    VBox.setMargin(startNewGame, new Insets(25, 0, 0, 0));
    startNewGame.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_16));
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
					switch (game.getDifficulty()) {
						case BEGINNER:
							player.setGold(12000);
						case NORMAL:
							player.setGold(10000);
						case ADVANCED:
							player.setGold(8000);
					}
          game.getPlayers().add(player);
        }
        for (int i = totalPlayers; i < 3; i++) {
          Player player = new Player();
          player.setComputer(true);
          player.setColor(Player.COLORS[i]);
          player.setName(Player.NAMES[i - totalPlayers]);
					switch (game.getDifficulty()) {
						case BEGINNER:
							player.setGold(8000);
						case NORMAL:
							player.setGold(10000);
						case ADVANCED:
							player.setGold(12000);
					}
          game.getPlayers().add(player);
        }

				WorldGenerator worldGenerator = (WorldGenerator) worldCreation.getSelectedToggle().getUserData();
        game.setWorld(worldGenerator.generate(64, 256));

        genesia.setGame(game);
      }
    });

    outerPane.getChildren().addAll(numberOfPlayersLabel, numberOfPlayersButtons,
            horizontalLine1, worldCreationLabel, worldCreationButtons,
            horizontalLine2, gameLevelLabel, gameLevelButtons,
            startNewGame);

    getChildren().addAll(backgroundRect, background, outerPane);
  }
}
