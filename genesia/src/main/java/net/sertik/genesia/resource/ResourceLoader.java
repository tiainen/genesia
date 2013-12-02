package net.sertik.genesia.resource;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import net.sertik.genesia.entity.GameObject;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.ui.TileNode;

/**
 *
 * @author joeri
 */
public abstract class ResourceLoader {
	private World world;

	public ResourceLoader(World world) {
		this.world = world;
	}

	public abstract Node createResource(GameObject gameObject);

  public TileNode createResource(Tile tile) {
		if (! tile.getObjects().isEmpty()) {
			TileNode tileNode = new TileNode(tile);
			for (GameObject gameObject : tile.getObjects()) {
				tileNode.getChildren().add(createResource(gameObject));
			}

			Node landBorder = createLandBorder(tile.getX(), tile.getY());
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
	 * @return the lines required to draw the border of tile at the specified coordinates
	 */
	private Node createLandBorder(int x, int y) {
		if (x == 0 && y == 0) {
			// north east corner
			Polygon polygon = new Polygon();
			polygon.getPoints().addAll(
							World.TILE_WIDTH / 2.0, 0.0,
							World.TILE_WIDTH * 1.0, World.TILE_HEIGHT / 2.0,
							World.TILE_WIDTH / 2.0, World.TILE_HEIGHT * 1.0,
							0.0, World.TILE_HEIGHT / 2.0);
			polygon.setStrokeType(StrokeType.INSIDE);
			polygon.getStrokeDashArray().addAll(1.0, 2.0);
			polygon.setStroke(Color.WHITE);
			polygon.setStrokeWidth(1.0);
			polygon.setFill(Color.TRANSPARENT);
			return polygon;
		} else if (x == 0 && y == world.getNumberTilesPerLandSqrt() - 1) {
			// north west corner
			Polygon polygon = new Polygon();
			polygon.getPoints().addAll(
							World.TILE_WIDTH / 2.0, 0.0,
							World.TILE_WIDTH * 1.0, World.TILE_HEIGHT / 2.0,
							World.TILE_WIDTH / 2.0, World.TILE_HEIGHT * 1.0,
							0.0, World.TILE_HEIGHT / 2.0);
			polygon.setStrokeType(StrokeType.INSIDE);
			polygon.getStrokeDashArray().addAll(1.0, 2.0);
			polygon.setStroke(Color.RED);
			polygon.setStrokeWidth(1.0);
			polygon.setFill(Color.TRANSPARENT);
			return polygon;
		} else if (x == world.getNumberTilesPerLandSqrt() - 1 && y == 0) {
			// south east corner
			Polygon polygon = new Polygon();
			polygon.getPoints().addAll(
							World.TILE_WIDTH / 2.0, 0.0,
							World.TILE_WIDTH * 1.0, World.TILE_HEIGHT / 2.0,
							World.TILE_WIDTH / 2.0, World.TILE_HEIGHT * 1.0,
							0.0, World.TILE_HEIGHT / 2.0);
			polygon.setStrokeType(StrokeType.INSIDE);
			polygon.getStrokeDashArray().addAll(1.0, 2.0);
			polygon.setStroke(Color.BLUE);
			polygon.setStrokeWidth(1.0);
			polygon.setFill(Color.TRANSPARENT);
			return polygon;
		} else if (x == world.getNumberTilesPerLandSqrt() - 1 && y == world.getNumberTilesPerLandSqrt() - 1) {
			// south west corner
			Polygon polygon = new Polygon();
			polygon.getPoints().addAll(
							World.TILE_WIDTH / 2.0, 0.0,
							World.TILE_WIDTH * 1.0, World.TILE_HEIGHT / 2.0,
							World.TILE_WIDTH / 2.0, World.TILE_HEIGHT * 1.0,
							0.0, World.TILE_HEIGHT / 2.0);
			polygon.setStrokeType(StrokeType.INSIDE);
			polygon.getStrokeDashArray().addAll(1.0, 2.0);
			polygon.setStroke(Color.BLACK);
			polygon.setStrokeWidth(1.0);
			polygon.setFill(Color.TRANSPARENT);
			return polygon;
		} else if (x % world.getNumberTilesPerLandSqrt() == 0) {
			// northern border
			Line line = new Line();
			line.setStartX(0.0);
			line.setStartY(World.TILE_HEIGHT / 2.0);
			line.setEndX(World.TILE_WIDTH / 2.0);
			line.setEndY(0.0);
			line.getStrokeDashArray().addAll(1.0, 2.0);
			line.setStroke(Color.WHITE);
			line.setStrokeWidth(1.0);
			line.setFill(Color.TRANSPARENT);
			return line;
		} else if (x % world.getNumberTilesPerLandSqrt() == world.getNumberTilesPerLandSqrt() - 1) {
			// southern border
			Line line = new Line();
			line.setStartX(World.TILE_WIDTH);
			line.setStartY(World.TILE_HEIGHT / 2.0);
			line.setEndX(World.TILE_WIDTH / 2.0);
			line.setEndY(World.TILE_HEIGHT);
			line.getStrokeDashArray().addAll(1.0, 2.0);
			line.setStroke(Color.WHITE);
			line.setStrokeWidth(1.0);
			line.setFill(Color.TRANSPARENT);
			return line;
		} else if (y % world.getNumberTilesPerLandSqrt() == 0) {
			// eastern border
			Line line = new Line();
			line.setStartX(World.TILE_WIDTH / 2.0);
			line.setStartY(World.TILE_HEIGHT);
			line.setEndX(World.TILE_WIDTH);
			line.setEndY(World.TILE_HEIGHT / 2.0);
			line.getStrokeDashArray().addAll(1.0, 2.0);
			line.setStroke(Color.WHITE);
			line.setStrokeWidth(1.0);
			line.setFill(Color.TRANSPARENT);
			return line;
		} else if (y %  world.getNumberTilesPerLandSqrt() ==  world.getNumberTilesPerLandSqrt() - 1) {
			// western border
			Line line = new Line();
			line.setStartX(World.TILE_WIDTH / 2.0);
			line.setStartY(World.TILE_HEIGHT);
			line.setEndX(0.0);
			line.setEndY(World.TILE_HEIGHT / 2.0);
			line.getStrokeDashArray().addAll(1.0, 2.0);
			line.setStroke(Color.WHITE);
			line.setStrokeWidth(1.0);
			line.setFill(Color.TRANSPARENT);
			return line;
		}
		return null;
	}
}
