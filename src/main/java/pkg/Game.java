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
	public Player player1;
	/**
	 * The other player of the game.
	 */
	public Player player2;

	/**
	 * The game table in a 10*10 integer matrix format.
	 */
	public int table[][] = new int[10][10];

	/**
	 * Scanner object for the user's I/O operations.
	 */
	Scanner scanner;

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
	 * Prints the game table to the console ouptut.
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

	/***
	 * Sets the players datas from the console input.
	 */
	public void setPlayerDatas() {
		scanner = new Scanner(System.in);
		player1 = new Player();
		player2 = new Player();
		System.out.println("Kerem az elso jatekos nevet!");
		player1.setName(scanner.next());
		player1.setCode(1);
		System.out.println("Kerem a masodik jatekos nevet!");
		player2.setName(scanner.next());
		player2.setCode(2);
		logger.info("All players data setted.");
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
		logger.info("A játék döntetlennel ért véget, a táblán nincs elhelyezhetõ lépés.");
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

		try {
			System.out.println(player.getName() + " kovetkezik!");
			System.out.println("Sor: ");
			int i = scanner.nextInt();
			System.out.println("Oszlop: ");
			int j = scanner.nextInt();
			table[i - 1][j - 1] = player.getCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				int l;
				for (l = 1; l < 5; l++) {
					if (!(table[i][j] == table[i][j + l])
							&& table[i][j] == player.getCode()) {
						break;
					}
				}
				if (l == 4) {
					logger.info(player.getName() + " jobb irányban nyert!");
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
				int l;
				for (l = 1; l < 5; l++) {
					if (!(table[i][j] == table[i + l][j])
							&& table[i][j] == player.getCode()) {
						break;
					}
				}
				if (l == 4) {
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
				int l;
				for (l = 1; l < 5; l++) {
					if (!(table[i][j] == table[i + l][j - l])
							&& table[i][j] == player.getCode()) {
						break;
					}
				}
				if (l == 4) {
					logger.info(player.getName() + " bal-le irányban nyert!");
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
				int l;
				for (l = 1; l < 5; l++) {
					if (!(table[i][j] == table[i + l][j + l])
							&& table[i][j] == player.getCode()) {
						break;
					}
				}
				if (l == 4) {
					logger.info(player.getName() + " jobb-le irányban nyert!");
					return true;
				}
			}
		}
		return false;
	}

}
