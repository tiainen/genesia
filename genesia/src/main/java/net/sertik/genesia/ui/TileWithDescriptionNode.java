package net.sertik.genesia.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.media.Assets;

/**
 *
 * @author Skyora
 */
public class TileWithDescriptionNode extends VBox {
	private TileNode tileNode;
	private Label landName;
	private Label description;

	public TileWithDescriptionNode() {
		setSpacing(15.0);
		setAlignment(Pos.CENTER);

		landName = new Label();
		landName.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_16));
		description = new Label();
		description.setFont(Assets.getFonts().get(Assets.FONT_COURIERNEW_BOLD_13));

		VBox labels = new VBox();
		labels.setAlignment(Pos.CENTER);
		labels.getChildren().addAll(landName, description);

		getChildren().add(labels);
	}

	public TileNode getTileNode() {
		return tileNode;
	}

	public void setTileNode(TileNode tileNode) {
		if (this.tileNode != null) {
			getChildren().remove(0);
		}

		this.tileNode = tileNode;
		getChildren().add(0, this.tileNode);

		Tile tile = this.tileNode.getTile();
		if (! tile.getObjects().isEmpty()) {
			landName.setText(tile.getLand().getName());
			description.setText(tile.toString());
		} else {
			landName.setText("");
			description.setText("");
		}
	}
}
