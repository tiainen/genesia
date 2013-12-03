package net.sertik.genesia;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sertik.genesia.entity.Game;
import net.sertik.genesia.media.Assets;
import net.sertik.genesia.resource.ImageResourceLoader;
import net.sertik.genesia.resource.PolygonResourceLoader;
import net.sertik.genesia.render.Renderer;
import net.sertik.genesia.render.QuadTreeRenderer;
import net.sertik.genesia.render.SimpleRenderer;
import net.sertik.genesia.resource.ResourceLoader;
import net.sertik.genesia.screen.AskPlayerName;
import net.sertik.genesia.screen.GameSelection;
import net.sertik.genesia.screen.MainGame;

/**
 *
 * @author joeri
 */
public class Genesia extends Application {

	private Game game;

	private double stageInitialX;
	private double stageInitialY;
	private double stageInitialWidth;
	private double stageInitialHeight;

	private double screenWidth;
	private double screenHeight;

	private Stage stage;
	private Scene introScene;
	private Scene mainScene;

	public static void main(String[] args) {
		Application.launch(Genesia.class, args);
	}

	@Override
	public void start(Stage primaryStage) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode defaultDisplayMode = ge.getDefaultScreenDevice().getDisplayMode();
		screenWidth = defaultDisplayMode.getWidth();
		screenHeight = defaultDisplayMode.getHeight();

		Assets.initialize();

		stage = primaryStage;
		stage.setTitle("Genesia");
		stage.setResizable(false);

		introScene = new Scene(new GameSelection(this), 640, 400);
		introScene.getStylesheets().add("net/sertik/genesia/default.css");

		mainScene = new Scene(new Group(), screenWidth - 200, screenHeight - 200);

		stage.setScene(introScene);
		stage.show();

		stageInitialX = stage.getX();
		stageInitialY = stage.getY();
		stageInitialWidth = stage.getWidth();
		stageInitialHeight = stage.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
		if (this.game == null) {
			stage.setX(stageInitialX);
			stage.setY(stageInitialY);
			stage.setWidth(stageInitialWidth);
			stage.setHeight(stageInitialHeight);
			introScene.setRoot(new GameSelection(this));
			stage.setScene(introScene);
		} else {
			askNextPlayerName(0);
		}
	}

	public void askNextPlayerName(int player) {
		if (player == 3 || game.getPlayers().get(player).getComputer()) {
//			ResourceLoader resourceLoader = new PolygonResourceLoader(game.getWorld());
			ResourceLoader resourceLoader = new ImageResourceLoader(game.getWorld());
//      Renderer renderer = new SimpleRenderer();
			Renderer renderer = new QuadTreeRenderer(resourceLoader, game.getWorld());

			MainGame mainGame = new MainGame(this, screenWidth - 200, screenHeight - 200);
			mainGame.setRenderer(renderer);

			mainGame.render();
			mainScene.setRoot(mainGame);
			stage.setX(100);
			stage.setY(100);
			stage.setWidth(screenWidth - 200);
			stage.setHeight(screenHeight - 200);
			stage.setScene(mainScene);
		} else {
			introScene.setRoot(new AskPlayerName(this, game.getPlayers().get(player), player));
		}
	}
}
