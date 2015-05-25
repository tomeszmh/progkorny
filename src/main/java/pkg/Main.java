package pkg;

/**
 * The main class of the application.
 * 
 *
 */
public class Main {
	/**
	 * The main method of the application.
	 * @param  args The command line arguments
	 *
	 */
	public static void main(String[] args) {
		
		Game game = new Game();
		game.setPlayerDatas();
		game.printTable();
		int counter = 0;
		while (true) {
			if (counter % 2 == 0) {
				counter++;
				game.nextStep(game.player1);
				if (game.checkTable(game.player1)) {
					System.out.println("Jatek vege, " + game.player1.getName()
							+ " nyert!");
					System.exit(0);
				}
				if (game.checkTie()) {
					System.out.println("Jatek vege, dontetlen!");
					System.exit(0);
				}
			} else {
				counter--;
				game.nextStep(game.player2);
				game.checkTable(game.player2);
				if (game.checkTable(game.player2)) {
					System.out.println("Jatek vege, " + game.player2.getName()
							+ " nyert!");
					System.exit(0);
				}
				if (game.checkTie()) {
					System.out.println("Jatek vege, dontetlen!");
					System.exit(0);
				}
			}
			game.printTable();
		}
	}
}
