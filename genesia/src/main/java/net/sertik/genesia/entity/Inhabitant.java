package net.sertik.genesia.entity;

/**
 *
 * @author joeri
 */
public class Inhabitant {
  private Construction house;
  private int trade;
  private int age;

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Construction getHouse() {
    return house;
  }

  public void setHouse(Construction house) {
    this.house = house;
  }

  public int getTrade() {
    return trade;
  }

  public void setTrade(int trade) {
    this.trade = trade;
  }
}
