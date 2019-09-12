package ca.nait.dmit.domain;

/**
 * In the Chinese calendar, every year is associated with a particular animal. The 12-year animal cycle is rat, ox, tiger,
rabbit, dragon, snake, horse, goat (or ram), monkey, rooster, dog, and pig (or boar). The year 1900 is a year of the rat;
thus 1901 is a year of the ox and 1912 is another year of the rat. If you know in what year person wa
 * 
 * @author Sam Wu
 * @version 2019.09.12
 */
public class ChineseZodiac {

	/**
	 * Determines the aninmal name for a given year according to the Chinese Zodiac
	 * @param birthYear
	 * @return the animal name for the given year
	 */
	public static String animal(int birthYear) {
		int offset = (birthYear - 1900) % 12;
		switch (offset) {
		case 0:
			return "Rat";
		case 1:
			return "Ox";
		case 2:
			return "Tiger";
		case 3:
			return "Rabbit";
		case 4:
			return "Dragon";
		case 5:
			return "Snake";
		case 6:
			return "Horse";
		case 7:
			return "Goat";
		case 8:
			return "Monkey";
		case 9:
			return "Rooster";
		case 10:
			return "Dog";
		case 11:
			return "Pig";
		default:
			return "Logic Error";
		}
	}
}
