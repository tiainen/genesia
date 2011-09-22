package net.sertik.genesia.entity;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author joeri
 */
public class Game {
  public enum Difficulty {
    BEGINNER, NORMAL, ADVANCED
  }

  public enum Season {
    SPRING, SUMMER, AUTUMN, WINTER
  }

  private List<Player> players = new LinkedList<Player>();
  private World world;
  private Difficulty difficulty = Difficulty.NORMAL;
  private Season season = Season.AUTUMN;

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public Season getSeason() {
    return season;
  }

  public void setSeason(Season season) {
    this.season = season;
  }

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  public void process() {
    Land land = world.getLands().get(0);

  }
}
