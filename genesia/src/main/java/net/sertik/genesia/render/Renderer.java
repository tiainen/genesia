package net.sertik.genesia.render;

import javafx.scene.Group;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.resource.ResourceLoader;

/**
 *
 * @author Joeri
 */
public abstract class Renderer {
	protected ResourceLoader resourceLoader;
	protected World world;

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

  public abstract void render(Group container, double width, double height);
}
