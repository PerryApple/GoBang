import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Game {
	private static final int WHITE = -1;
	private static final int BLACK = 1;
	private int boardSize;
	private int[][] board;
	private static final int RECURSIVEDEPTH = 6;
	private int m;
	private Evaluate evaluate = new Evaluate();
	private EvaluatePositions evaluatePositions = new EvaluatePositions();
	//For Test
	//public Game() {}
	
	public Game(int n, int m) {
		boardSize = n;
		this.m = m;
		board = new int[n][n];
	}
	
	public void setBoardSize (int boardSize) {
		this.boardSize = boardSize;
	}
	
//begin a new game.
	public void game() {
		Status move = new Status(0,0,0);
		//randomly put the first piece
		move.column = boardSize/2;
		move.row = boardSize/2;
		board[move.row][move.column] = BLACK;
		System.out.println("( " + move.row + " , " + move.column + " )");
		//count the turn number
		int turn = 0;
		//second player is white, set color to WHITE
		int color = WHITE;
		//move = nextStep(color, 0,Integer.MIN_VALUE);
		//System.out.println("( " + move.row + " , " + move.column + " )");
		while(Win.haveAWin(board, m) == 0 && turn<(boardSize*boardSize-1)) {
			//call nextStep to get best next move position.
			if(color == WHITE){
				move = nextStep(color, 0,Integer.MIN_VALUE);
			}else{
				move = nextStep(color, 0,Integer.MAX_VALUE);
			}

			board[move.row][move.column] = color;
			turn++;
			
			//switch player
			color = 0-color;
			System.out.println("( " + move.row + " , " + move.column + " )");
			//System.out.println(Win.haveAWin(board, m));
			
		}
		
		//clean the game board
		for(int i = 0; i<boardSize ; i++) {
			for(int j =0; j<boardSize; j++) {
				board[i][j] = 0;
			}
		}
		System.out.println("");
		System.out.println("Board cleaned! ");
	}
	
//******************  MinMax  ******************
	//Calculate the best next move position
	//this is a recursive method.
	private Status nextStep(int color, int depth,int alphaBeta) {
		
		//store two status, bestMove and bestNextMove 
		//in Black player's turn we need to find the max value
		//in White player's turn we need to find the  negative max value 
		//1 means black win and -1 means white win.
		//set original bestMove point to their color.(B 1, W -1).
		
		//初始化
		//Initializing
		Status bestMove;
		Status bestNextMove;
		int nextLevelAlphaBeta;

		if(color == BLACK) {
			bestMove = new Status(0,0,Integer.MIN_VALUE);
			nextLevelAlphaBeta = Integer.MIN_VALUE;
		}else {
			bestMove = new Status(0,0,Integer.MAX_VALUE);
			nextLevelAlphaBeta = Integer.MAX_VALUE;
		}
		//判断递归终止条件
		//Judge if the game has a win or lose status.
		int winStatus = Win.haveAWin(board, m);
		if(winStatus != 0) {
			bestMove.point = (winStatus == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			return bestMove;
		}
		//Judge if we meet the recursive depth.
		if(depth == RECURSIVEDEPTH) {
			bestMove.point = evaluate.evaluateGame(board, m);
			return bestMove;
		}
		//用启发式搜索获得所有可以下棋的点
		List<Position> positionList = evaluatePositions.evaluatePosition(board, m, color);
		//For each 循环来逐个评估得分
		for(Position p : positionList) {
			int row = p.getRow();
			int column = p.getColumn();
			//put a piece
			board[row][column] = color;
			int nextColor = 0 - color;
			//recursively call the method to get its score from next board status.
			bestNextMove = nextStep(nextColor, depth+1,nextLevelAlphaBeta);
			if(color == BLACK) {
				//Alpha Beta 剪枝
				if(bestNextMove.point>=alphaBeta){
					board[row][column] = 0;
					bestNextMove.row = row;
					bestNextMove.column = column;
					return bestNextMove;
				}
				//如果找到了更好的落棋点就替换原来的
				if(bestMove.point <= bestNextMove.point) {
					//set point and position.
					bestMove.point = bestNextMove.point;
					bestMove.row = row;
					bestMove.column = column;
					nextLevelAlphaBeta = bestMove.point;
					//System.out.println("new point: " + bestMove.point);
				}
			}
			if(color == WHITE) {
				//Alpha Beta 剪枝
				if(bestNextMove.point<=alphaBeta) {
					board[row][column] = 0;
					bestNextMove.row = row;
					bestNextMove.column = column;
					return bestNextMove;
				}
				//如果找到了更好的落棋点就替换原来的
				if(bestMove.point >= bestNextMove.point) {
					//set point and position.
					bestMove.point = bestNextMove.point;
					bestMove.row = row;
					bestMove.column = column;
					nextLevelAlphaBeta = bestMove.point;
				}
			}
			//delete the temp piece.
			board[row][column] = 0;
		}
		//end the loop, we found the best move.
		return bestMove;
	}
}

//class to store the move status, include its position and win point.
class Status{
	public int column, row, point;
	public Status(int row, int column, int point) {
		this.column = column;
		this.row = row;
		this.point = point;
	}
	
}
