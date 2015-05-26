import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

import pkg.Game;
import pkg.Player;

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
		game.table[0][0] = game.player1.getCode();
		assertEquals(game.checkTie(), false);
		game.player1.setCode(1);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				game.table[i][j] = game.player1.getCode();
			}
		}
		assertEquals(game.checkTie(), true);
	}

	@Test
	public void testTable() {
		Game game = new Game();
		game.player1.setCode(1);
		game.player2.setCode(2);
		assertEquals(game.checkTable(game.player1), false);
		assertEquals(game.checkTable(game.player2), false);
		assertEquals(game.searchDown(game.player1), false);
		assertEquals(game.searchLeftBottom(game.player1), false);
		assertEquals(game.searchRight(game.player2), false);
		assertEquals(game.searchRightBottom(game.player1), false);

		game.table[0][0] = game.player1.getCode();
		for (int i = 2; i < 8; i++)
			game.table[0][i] = game.player1.getCode();
		assertEquals(game.checkTable(game.player1), true);
		game.table[1][9] = game.player2.getCode();
		game.printTable();
		game = new Game();
		game.player1.setCode(1);
		game.player2.setCode(2);

		for (int i = 1; i < 6; i++)
			game.table[i][0] = game.player1.getCode();
		assertEquals(game.checkTable(game.player1), true);
		game = new Game();
		game.player1.setCode(1);
		game.player2.setCode(2);

		int j = 4;
		for (int i = 1; i < 6; i++, j--) {
			game.table[i][j] = game.player1.getCode();
		}
		assertEquals(game.checkTable(game.player1), true);
		game.player1.setCode(1);
		game.player2.setCode(2);
		j = 0;
		for (int i = 0; i < 5; i++, j++) {
			game.table[i][j] = game.player1.getCode();
		}

		assertEquals(game.checkTable(game.player1), true);
	}

	@Test
	public void testNextStep() {
		Game game = new Game();
		game.player1.setCode(1);
		game.player2.setCode(2);

		ByteArrayInputStream in = new ByteArrayInputStream("1\n1\n".getBytes());
		game.scanner = new Scanner(in);
		game.nextStep(game.player1);
		assertEquals(game.table[0][0], 1);
	}

	@Test
	public void testPlayerDatas() {
		Game game = new Game();
		game.player1.setCode(1);
		game.player2.setCode(2);
		ByteArrayInputStream in = new ByteArrayInputStream(
				"Tomi\nLaci\n".getBytes());
		game.scanner = new Scanner(in);
		game.setPlayerDatas();
		assertEquals(true, game.player1.getName().equals("Tomi"));

		assertEquals(true, game.player2.getName().equals("Laci"));
	}

	@Test
	public void toStringTest() {
		Player player = new Player();
		player.setName("Tomi");
		player.setCode(1);
		assertEquals(true, player.toString()
				.equals("Player [name=Tomi code=1]"));
	}
}
