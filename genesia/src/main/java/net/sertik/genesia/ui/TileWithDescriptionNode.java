package net.sertik.genesia.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.media.Assets;

/**
 *
 * @author Skyora
 */
public class TileWithDescriptionNode extends VBox {
	private StackPane tileNodeGroup;
	private TileNode tileNode;
	private Label landName;
	private Label description;

	public TileWithDescriptionNode() {
		setAlignment(Pos.CENTER);

		landName = new Label("no land\nselected");
		landName.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_16));
		landName.setTextAlignment(TextAlignment.CENTER);
		description = new Label();
		description.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));

		tileNodeGroup = new StackPane();
		tileNodeGroup.setPrefHeight(128.0);		

		VBox labels = new VBox();
		labels.setAlignment(Pos.CENTER);
		labels.getChildren().addAll(landName, description);

		getChildren().addAll(tileNodeGroup, labels);
	}

	public TileNode getTileNode() {
		return tileNode;
	}

	public void setTileNode(TileNode tileNode) {
		this.tileNode = tileNode;
		tileNodeGroup.getChildren().clear();
		tileNodeGroup.getChildren().add(this.tileNode);

		Tile tile = this.tileNode.getTile();
		if (! tile.getObjects().isEmpty()) {
			landName.setText(tile.getLand().getName());
			description.setText(tile.toString());
		} else {
			landName.setText("no land\nselected");
			description.setText("");
		}
	}
}
