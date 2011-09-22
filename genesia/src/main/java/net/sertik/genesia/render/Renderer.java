package net.sertik.genesia.render;

import javafx.scene.Group;
import net.sertik.genesia.entity.World;
import net.sertik.genesia.resource.ResourceLoader;

/**
 *
 * @author Joeri
 */
public interface Renderer {
  public void setResourceLoader(ResourceLoader resourceLoader);

  public void setWorld(World world);

  public void render(Group container, double width, double height);
}
