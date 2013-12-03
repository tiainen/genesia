package net.sertik.genesia.resource;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import net.sertik.genesia.entity.GameObject;
import net.sertik.genesia.entity.Land;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.ui.TileNode;

/**
 *
 * @author joeri
 */
public abstract class ResourceLoader {
	private final World world;

	public ResourceLoader(World world) {
		this.world = world;
	}

	public World getWorld() {
		return world;
	}

	public abstract Node createResource(GameObject gameObject);

  public TileNode createResource(Tile tile) {
		if (! tile.getObjects().isEmpty()) {
			TileNode tileNode = new TileNode(tile);
			for (GameObject gameObject : tile.getObjects()) {
				tileNode.getChildren().add(createResource(gameObject));
			}

			Node landBorder = createLandBorder(tile.getX(), tile.getY(), tile.getLand());
			if (landBorder != null) {
				tileNode.getChildren().add(landBorder);
			}

			return tileNode;
		}


		return null;
	}

	/**
	 * Creates the piece of the border of the land as a white dashed line,
	 * depending on the tile's coordinates.
	 *
	 * @param x the x coordinate of the tile within the land
	 * @param y the y coordinate of the tile within the land
	 * @param land the land where the tile belongs to
	 * @return the lines required to draw the border of tile at the specified coordinates
	 */
	private Node createLandBorder(int x, int y, Land land) {
		if (x == 0 && y == 0) {
			// northeast corner
			Group lines = new Group();
			lines.getChildren().addAll(createNorthBorder(), createEastBorder());
			return lines;
		} else if (x == 0 && y == world.getNumberTilesPerLandSqrt() - 1 && land.getY() == world.getNumberLandsSqrt() - 1) {
			// northwest corner
			Group lines = new Group();
			lines.getChildren().addAll(createNorthBorder(), createWestBorder());
			return lines;
		} else if (x == world.getNumberTilesPerLandSqrt() - 1 && y == 0 && land.getX() == world.getNumberLandsSqrt() - 1) {
			// southeast corner
			Group lines = new Group();
			lines.getChildren().addAll(createEastBorder(), createSouthBorder());
			return lines;
		} else if (x == world.getNumberTilesPerLandSqrt() - 1 && y == world.getNumberTilesPerLandSqrt() - 1 &&
						land.getX() == world.getNumberLandsSqrt() - 1 && land.getY() == world.getNumberLandsSqrt() - 1) {
			// southwest corner
			Group lines = new Group();
			lines.getChildren().addAll(createWestBorder(), createSouthBorder());
			return lines;
		} else if (x % world.getNumberTilesPerLandSqrt() == 0) {
			return createNorthBorder();
		} else if (land.getX() == world.getNumberLandsSqrt() - 1 && x % world.getNumberTilesPerLandSqrt() == world.getNumberTilesPerLandSqrt() - 1) {
			return createSouthBorder();
		} else if (y % world.getNumberTilesPerLandSqrt() == 0) {
			return createEastBorder();
		} else if (land.getY() == world.getNumberLandsSqrt() - 1 && y %  world.getNumberTilesPerLandSqrt() ==  world.getNumberTilesPerLandSqrt() - 1) {
			return createWestBorder();
		}
		return null;
	}

	private Line createNorthBorder() {
		return createLine(0.0, World.TILE_HEIGHT / 2.0, World.TILE_WIDTH / 2.0, 0.0);
	}

	private Line createEastBorder() {
		return createLine(World.TILE_WIDTH / 2.0, 0.0, World.TILE_WIDTH, World.TILE_HEIGHT / 2.0);
	}

	private Line createSouthBorder() {
		return createLine(World.TILE_WIDTH, World.TILE_HEIGHT / 2.0, World.TILE_WIDTH / 2.0, World.TILE_HEIGHT);
	}

	private Line createWestBorder() {
		return createLine(World.TILE_WIDTH / 2.0, World.TILE_HEIGHT, 0.0, World.TILE_HEIGHT / 2.0);
	}

	private Line createLine(double startX, double startY, double endX, double endY) {
		Line line = new Line();
		line.setStartX(startX);
		line.setStartY(startY);
		line.setEndX(endX);
		line.setEndY(endY);
		line.getStrokeDashArray().addAll(1.0, 4.0);
		line.setStroke(Color.rgb(200, 200, 200));
		line.setStrokeWidth(1.0);
		line.setFill(Color.TRANSPARENT);
		return line;
	}
}
