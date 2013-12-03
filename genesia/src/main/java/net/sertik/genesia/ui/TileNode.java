package net.sertik.genesia.ui;

import javafx.scene.Group;
import net.sertik.genesia.entity.Tile;

/**
 *
 * @author Skyora
 */
public class TileNode extends Group {

	private Tile tile;

	public TileNode(Tile tile) {
		this.tile = tile;
	}

	public Tile getTile() {
		return tile;
	}
}
