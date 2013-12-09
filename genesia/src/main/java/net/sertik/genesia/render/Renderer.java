package net.sertik.genesia.render;

import javafx.scene.Group;
import javafx.scene.Node;
import net.sertik.genesia.entity.Scenery;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.resource.ResourceLoader;
import net.sertik.genesia.util.Coords;

/**
 *
 * @author Joeri
 */
public abstract class Renderer {

	protected final ResourceLoader resourceLoader;
	protected final World world;

	private Node hoverTile;

	public Renderer(ResourceLoader resourceLoader, World world) {
		if (resourceLoader == null) {
			throw new NullPointerException("No ResourceLoader specified.");
		}

		this.resourceLoader = resourceLoader;
		this.world = world;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public World getWorld() {
		return world;
	}

	public void render(Group container, double width, double height) {
		renderTiles(container, width, height);

		// render hover tile
		Coords hoverWorldCoords = world.getHoverWorldCoords();
		if (hoverWorldCoords.getX() != -1 && hoverWorldCoords.getY() != -1) {
			if (hoverTile == null) {
				hoverTile = resourceLoader.createResource(Scenery.HOVER_TILE);
				hoverTile.setUserData(new Integer(1));
				container.getChildren().add(hoverTile);
			}
			hoverTile.setLayoutX(World.TILE_WIDTH / 2 * (hoverWorldCoords.getX() - hoverWorldCoords.getY()));
			hoverTile.setLayoutY(World.TILE_HEIGHT / 2 * (hoverWorldCoords.getX() + hoverWorldCoords.getY()));
		}
	}

	protected abstract void renderTiles(Group container, double width, double height);
}
