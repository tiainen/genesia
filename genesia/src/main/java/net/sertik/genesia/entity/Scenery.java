package net.sertik.genesia.entity;

/**
 *
 * @author Joeri
 */
public class Scenery implements GameObject {
  private String name;

  public Scenery(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
