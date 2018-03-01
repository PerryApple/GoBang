import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EvaluateTest {
	public static void main(String[] args) {
		Evaluate e = new Evaluate();
//		int[][] board = {{0,0,-1,0,0,0,0,0},{0,0,-1,1,0,0,1,0},{0,0,1,-1,1,0,1,0},{0,0,0,0,-1,1,0,0},{0,0,0,0,1,0,1,0},{0,0,0,0,0,0,0,0},{-1,-1,0,0,0,0,0,0},{0,0,-1,-1,0,0,0,0}};
//		System.out.println(e.evaluateGame(1, board, 5));
//		int[][] board1 = {{0,0,-1,0,0,0,0,0},{0,0,-1,1,0,0,1,0},{0,0,1,-1,1,0,1,0},{-1,0,0,0,-1,1,0,0},{0,-1,0,0,1,0,1,0},{0,0,-1,0,0,0,0,0},{-1,-1,0,-1,0,0,0,0},{0,0,-1,-1,-1,0,0,0}};
//		System.out.println(Win.haveAWin(board1, 5));
		//int[][] board1 = {{0,1,-1,1},{0,0,-1,1},{0,0,1,-1},{0,1,0,0}};
		//System.out.println(e.evaluateGame(board1, 3));
		//System.out.println(Win.haveAWin(board1, 3));
		//int[][] board = {{-1,0,0,0,0,0},{0,1,0,-1,1,0},{0,0,1,-1,1,0},{0,0,-1,1,1,0},{0,0,0,0,-1,0},{0,0,0,0,0,0}};
		//Game game = new Game();
		Game game = new Game(10, 5);
		game.game();
		//int[][] board = {{1,1,1,1,1,-1,0,0}};
		//System.out.println(Win.haveAWin(board, 5));
		
		//*******Test of sort****************
//		Position p1 = new Position(1,1,1);
//		Position p2 = new Position(1,3,10);
//		Position p3 = new Position(1,2,-10);
//		Position p4 = new Position(1,4,-10);		
//		Position p5 = new Position(1,5,-10);
//		List<Position> positionList = new ArrayList<Position>();
//		positionList.add(p1);
//		positionList.add(p2);
//		positionList.add(p3);
//		positionList.add(p4);
//		positionList.add(p5);
//		Collections.sort(positionList);
//		positionList = positionList.subList(1, 3);
//		for(Position p : positionList) {
//			System.out.println(p.toString());
//		}	
	}
}
