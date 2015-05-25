import static org.junit.Assert.*;

import org.junit.Test;

import pkg.Game;

public class GameTest {

	@Test
	public void testGameConstructor() {
		Game game = new Game();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assertEquals(game.table[i][j], 0);
			}
		}
	}

	@Test
	public void testTie() {
		Game game = new Game();
		assertEquals(game.checkTie(), false);
	}

	@Test
	public void test() {
		Game game = new Game();
		assertEquals(game.checkTable(game.player1), false);
		assertEquals(game.checkTable(game.player1), false);
		assertEquals(game.checkTable(game.player2), false);
		assertEquals(game.searchDown(game.player1), false);
		assertEquals(game.searchLeftBottom(game.player1), false);
		assertEquals(game.searchRight(game.player2), false);
		assertEquals(game.searchRightBottom(game.player1), false);
		for (int i = 0; i < 5; i++)
			game.table[0][i] = game.player1.getCode();
		assertEquals(game.checkTable(game.player1), true);
	}
}
