package net.sertik.genesia.screen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import net.sertik.genesia.Genesia;
import net.sertik.genesia.entity.Player;
import net.sertik.genesia.media.Assets;

/**
 *
 * @author joeri
 */
public class AskPlayerName extends StackPane {

	public AskPlayerName(final Genesia genesia, final Player player, final int position) {
		Rectangle backgroundRect = new Rectangle(640, 400);
		backgroundRect.setFill(Color.BLACK);

		final ImageView background = new ImageView();
		background.setOpacity(0.3);
		background.setImage(Assets.getBackground(Assets.IMAGE_SCREENS_GAME_SELECTION_BACKGROUND));

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(15);
		vbox.setPadding(new Insets(25, 25, 25, 25));

		Label label = new Label("Enter your name Player " + (position + 1));
		label.setFont(Assets.getFont(Assets.FONT_COURIERNEW_BOLD_16));
		label.setTextFill(Color.LIGHTGRAY);

		final TextField name = new TextField();
		name.setPrefColumnCount(15);
		name.setText("Player " + (position + 1));
		name.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				player.setName(name.getText());
				name.setDisable(true);
				genesia.askNextPlayerName(position + 1);
			}
		});

		vbox.getChildren().addAll(label, name);

		getChildren().addAll(backgroundRect, background, vbox);
	}
}
