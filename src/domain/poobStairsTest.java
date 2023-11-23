package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class poobStairsTest {

	@Test
	void test() {
		try {
			PoobStairs game = new PoobStairs(5, 5, 5, 5, 0.0,"","",new Color(1,2,3),new Color(0,9,8),60,true);
			Player player1 = game.getPlayer(1);
			Player player2 = game.getPlayer(2);
			player2.getPiece().setPosition(22);
			int [] dice = {6,3};
			System.out.println(player1.isMultiply());
			int [] aux =  game.playWithOptionTest(player1, player2, dice).get(0);
			assertEquals(dice[1],aux[1]);
			assertTrue(dice[1]== aux[1]);
			int [] aux1 =  game.playWithOptionTest(player1, player2, dice).get(0);
			assertTrue(0== aux[1]);
			
			
		} catch (PoobStairsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int[] getCoordSpecialBox(Box[][] boxes) {
		for(int i =  0; i<boxes.length;i++) {
			for(int j = 0 ; j<boxes[0].length;j++) {
				if(! (boxes[i][j] instanceof NormalBox)) {
					return new int[] {i,j};				}
			}
		}
		return null;
	}
	
	public int[] getCoordSnakeStair(Box[][] boxes) {
		for(int i =  0; i<boxes.length;i++) {
			for(int j = 0 ; j<boxes[0].length;j++) {
				if(boxes[i][j] instanceof SnakeStair) {
					return new int[] {i,j};				}
			}
		}
		return null;
	}
	
	@Test
	public void mustBeganPoobStairsWithExceptions() {
		try {
			PoobStairs game = new PoobStairs(5, -1, 5, 5, 0.0,"","",new Color(1,2,3),new Color(0,9,8),60,true);
		}catch (PoobStairsException e) {
			assertEquals(e.getMessage(), e.INVALIDVALUE);
		}
	}
	
	@Test
	public void mustMovePiece() throws PoobStairsException{
		PoobStairs game = new PoobStairs(5, 5, 5, 5, 0.0,"","",new Color(1,2,3),new Color(0,9,8),60,true);
		game.movePlayer(1, 6);
		assertTrue(game.getPlayer1().getPos()!=0);
	}
	
	@Test
	public void mustPlayDice() throws PoobStairsException{
		PoobStairs game = new PoobStairs(5, 5, 5, 5, 0.0,"","",new Color(1,2,3),new Color(0,9,8),60,true);
		int number  = game.getDice().playDice();
		assertTrue(0<number && number<7);
	}
	
	@Test
	public void mustPutSpecialBoxes() throws PoobStairsException{
		PoobStairs game = new PoobStairs(5, 5, 5, 5, 50,"","",new Color(1,2,3),new Color(0,9,8),60,true);
		Box box  = game.getBoard().getBox(getCoordSpecialBox(game.getBoxes()));
		assertFalse(box instanceof NormalBox);
	}
	
	@Test
	public void mustPutSpecialSnakeStairs() throws PoobStairsException{
		PoobStairs game = new PoobStairs(5, 5, 5, 5, 50,"","",new Color(1,2,3),new Color(0,9,8),60,true);
		Box box  = game.getBoard().getBox(getCoordSpecialBox(game.getBoxes()));
		assertTrue(box instanceof SnakeStair);
	}
	
	@Test
	public void mustPutSpecialTransformerSnakeStairs() throws PoobStairsException{
		PoobStairs game = new PoobStairs(5, 5, 5, 5, 50,"","",new Color(1,2,3),new Color(0,9,8),60,true);
		Box box  = game.getBoard().getBox(getCoordSpecialBox(game.getBoxes()));
		assertTrue(box instanceof DualSnakeStair || box instanceof WeekSnakeStair );
	}
	
	
	
	@Test
	public void mustPlayDiceWithModifier() throws PoobStairsException{
		PoobStairs game = new PoobStairs(5, 5, 5, 5, 0.0,"","",new Color(1,2,3),new Color(0,9,8),100,true);
		int[] diceWithModifier = game.getDice().playDiceWithModifier();
		assertTrue(diceWithModifier[1] != 0);
		
	}

}
