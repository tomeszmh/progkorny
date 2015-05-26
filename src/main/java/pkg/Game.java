package pkg;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for handling the game main methods.
 * 
 *
 * 
 */
public class Game {
	/**
	 * The logger object of the class.
	 */
	Logger logger = LoggerFactory.getLogger(Game.class);
	/**
	 * The starting player of the game.
	 */
	public Player player1 = new Player();
	/**
	 * The other player of the game.
	 */
	public Player player2 = new Player();

	/**
	 * The game table in a 10*10 integer matrix format.
	 */
	public int table[][] = new int[10][10];

	/**
	 * Scanner object for the user's I/O operations.
	 */
	public Scanner scanner = new Scanner(System.in);;

	/**
	 * Creates an empty game table and fills the matrix with zero elements.
	 */
	public Game() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				table[i][j] = 0;
			}
		}
	}

	/**
	 * Sets the player datas, and start the game.
	 */
	public void startGame() {
		setPlayerDatas();
	}

	/**
	 * Prints the game table to the console output.
	 */
	public void printTable() {
		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				System.out.print("    ");
				for (int l = 1; l < 11; l++) {
					System.out.print(l + " ");
				}
				System.out.println();
			}
			if (i != 9)
				System.out.print((i + 1) + "   ");
			else
				System.out.print((i + 1) + "  ");
			for (int j = 0; j < 10; j++) {
				if (table[i][j] == 0) {
					System.out.print(". ");
				} else if (table[i][j] == 1) {
					System.out.print("X ");
				} else {
					System.out.print("O ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Set the players datas and starts the game.
	 */
	public void setPlayerDatas() {
		player1 = new Player();
		player2 = new Player();
		System.out.println("Kerem az elso jatekos nevet!");
		player1.setName(scanner.next());
		player1.setCode(1);
		System.out.println("Kerem a masodik jatekos nevet!");
		player2.setName(scanner.next());
		player2.setCode(2);
		logger.info("All players data setted.");
		logger.info("A jatek elkezdodott!");
	}

	/**
	 * Checks if the table has no possible step.
	 * 
	 * @return <code>true</code> if there is no possible step.
	 */
	public boolean checkTie() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (table[i][j] == 0) {
					return false;
				}
			}

		}
		printTable();
		logger.info("A jatek dontetlennel ert veget, a tablan nincs elhelyezheto lepes.");
		return true;
	}

	/**
	 * Adds the player's code to the right position on the table.
	 * 
	 * @param player
	 *            the player who will take the next step
	 * 
	 */
	public void nextStep(Player player) {
		System.out.println(player.getName() + " kovetkezik!");
		System.out.println("Sor: ");
		int i = scanner.nextInt();
		System.out.println("Oszlop: ");
		int j = scanner.nextInt();
		table[i - 1][j - 1] = player.getCode();
		logger.debug(player1.getName() + " jatekos lepett a(z) " + i + ". sor "
				+ j + ". mezőjére.");
	}

	/**
	 * Check the table by searching a winning sequence in the table.
	 * 
	 * @param player
	 *            who took the latest step
	 * @return <code>true</code> if there is a winning sequence in the table
	 */
	public boolean checkTable(Player player) {

		return searchRightBottom(player) || searchLeftBottom(player)
				|| searchDown(player) || searchRight(player);
	}

	/**
	 * Checks if there's a winning combination on the table to right.
	 * 
	 * @param player
	 *            the player who took the latest step.
	 * @return <code>true</code> if there is a winning sequence in the table
	 */
	public boolean searchRight(Player player) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 6; j++) {
				int counter = 0;
				for (int l = 1; l < 5; l++) {
					if (!((table[i][j] == table[i][j + l]) && table[i][j] == player
							.getCode())) {

						break;
					}
					counter++;
				}
				if (counter == 4) {
					logger.info(player.getName() + " jobb iranyban nyert!");
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Checks if there's a winning combination on the table to down.
	 * 
	 * @param player
	 *            the player who took the latest step.
	 * @return <code>true</code> if there's a winning combination
	 */
	public boolean searchDown(Player player) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 10; j++) {
				int counter = 0;
				for (int l = 1; l < 5; l++) {
					if (!((table[i][j] == table[i + l][j]) && table[i][j] == player
							.getCode())) {
						break;
					}
					counter++;
				}
				if (counter == 4) {
					logger.info(player.getName() + " lefele nyert!");
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Checks if there's a winning combination on the table to the left-bottom.
	 * 
	 * @param player
	 *            the player who took the latest step.
	 * @return <code>true</code> if there's a winning combination
	 */
	public boolean searchLeftBottom(Player player) {
		for (int i = 0; i < 6; i++) {
			for (int j = 4; j < 10; j++) {
				int counter = 0;
				for (int l = 1; l < 5; l++) {

					if (!((table[i][j] == table[i + l][j - l]) && table[i][j] == player
							.getCode())) {
						break;
					}
					counter++;
				}
				if (counter == 4) {
					logger.info(player.getName() + " bal-le iranyban nyert!");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if there's a winning combination on the table to the right-bottom.
	 * 
	 * @param player
	 *            the player who took the latest step.
	 * @return <code>true</code> if there's a winning combination
	 */
	public boolean searchRightBottom(Player player) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				int counter = 0;
				for (int l = 1; l < 5; l++) {
					if (!((table[i][j] == table[i + l][j + l]) && table[i][j] == player
							.getCode())) {
						break;
					}
					counter++;
				}
				if (counter == 4) {
					logger.info(player.getName() + " jobb-le iranyban nyert!");
					return true;
				}
			}
		}
		return false;
	}

}
