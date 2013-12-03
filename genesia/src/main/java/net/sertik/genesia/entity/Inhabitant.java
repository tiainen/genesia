package net.sertik.genesia.entity;

/**
 * An Inhabitant defines a settler that comes to live in a certain Land. In
 * order for an settler to become an inhabitant of a land, this land will need
 * to fulfill a few criteria. For instance, it requires a vacant house, plenty
 * of food and water, no epidemics, etc. A second way to attract inhabitants is
 * by natural birth, when two inhabitants decide to live together and have
 * babies. After a certain age, these children will become old enough to be able
 * to start a job.
 *
 * @author joeri
 */
public class Inhabitant {

	public enum Trade {

		SETTLER,
		ARCHITECT,
		BLACKSMITH,
		CARPENTER,
		EXPLORER,
		FARMER,
		INVENTOR,
		WOODCUTTER
	}

	private Construction house;
	private Trade trade;
	private int age;

	/**
	 * Returns the age in years of an inhabitant. At a certain age they will die
	 * and young and older people will also be more vulnerable for diseases. The
	 * age also defines when an inhabitant is eligible for a job.
	 *
	 * @return the age of the inhabitant
	 */
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Returns the house this inhabitant lives in and therefor also defines in
	 * which land they live in.
	 *
	 * @return the house of the inhabitant
	 */
	public Construction getHouse() {
		return house;
	}

	public void setHouse(Construction house) {
		this.house = house;
	}

	/**
	 * Returns the job of the inhabitant.
	 *
	 * @return the job of the inhabitant
	 */
	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}
}
