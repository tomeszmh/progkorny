package pkg;

/**
 * Class for representing a player.
 * 
 *
 */

public class Player {
	/**
	 * The name of the player.
	 */
	String name;

	/**
	 * The player's code in the game table.
	 */
	int code;

	/***
	 * Returns the name of the player.
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the player.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the code of the player.
	 * 
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the code of the player.
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * The String representation of the player.
	 * 
	 */
	@Override
	public String toString() {
		return "Player [name=" + name + " code=" + code + "]";
	}

}
