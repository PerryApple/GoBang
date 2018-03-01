import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EvaluatePositions {
	private final static int POSITIONNUMBER = 8;
	
	private Evaluate evaluate = new Evaluate();
	public List<Position> evaluatePosition(int[][] board, int m, int color){
		//找出所有两格距离内有邻居的节点
		//Find all nodes that have neighbors within 2 steps.
		List<Position> positionList = new ArrayList<Position>();
		//遍历所有点
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board.length ; j++) {
				//如果该点没有棋子且周围两格有邻居
				if(board[i][j] == 0 && hasNeighbors(board, i , j)) {
					board[i][j] = color;
					//判断输赢，如果有输赢则直接返回只有该点的ArrayList.
					if(Win.haveAWin(board, m) != 0) {
						positionList.clear();
						positionList.add(new Position(i,j,0));
						//清空该点棋子
						board[i][j] = 0;
						return positionList;
					}
					//如果没有出现输赢，则对该下法进行评分然后放入ArrayList中
					Position p = new Position(i,j,evaluate.evaluateGame(board, m));
					positionList.add(p);
					//清空该点棋子
					board[i][j] = 0;
				}
			}
		}
		//遍历结束，按分数对点进行排序
		Collections.sort(positionList);
		if(color == -1) {
			Collections.reverse(positionList);
		}
		if(positionList.size()>POSITIONNUMBER) {
			positionList = positionList.subList(0, POSITIONNUMBER);
		}
		return positionList;
	}
	
	private boolean hasNeighbors(int[][] board, int row, int column) {
		//Check upward 向上检查
		if (row != 0) {
			if(board[row - 1][column] != 0) {
				return true;
			}if(row != 1) {
				if(board[row - 2][column] != 0) {
					return true;
				}
			}
		}
		//Check downward 向下检查
		if(row != board.length - 1) {
			if(board[row + 1][column] != 0) {
				return true;
			}if(row != board.length - 2) {
				if(board[row + 2][column] != 0) {
					return true;
				}
			}
		}
		//Check left side 检查左侧
		if(column != 0) {
			if(board[row][column - 1] != 0) {
				return true;
			}if(column != 1) {
				if(board[row][column - 2] != 0) {
					return true;
				}
			}
		}
		//Check right side 检查右侧
		if(column != board.length - 1) {
			if(board[row][column + 1] != 0) {
				return true;
			}if(column != board.length - 2) {
				if(board[row][column + 2] != 0) {
					return true;
				}
			}
		}
		//Check leftup
		if(column != 0 && row != 0) {
			if(board[row - 1][column - 1] != 0) {
				return true;
			}if(column != 1 && row != 1) {
				if(board[row - 2][column - 2] != 0) {
					return true;
				}
			}
		}
		//Check leftdown
		if(column != 0 && row != board.length - 1) {
			if(board[row + 1][column - 1] != 0) {
				return true;
			}if(column != 1 && row != board.length - 2) {
				if(board[row + 2][column - 2] != 0) {
					return true;
				}
			}
		}
		//Check rightdown
		if(column != board.length - 1 && row != board.length - 1) {
			if(board[row + 1 ][column + 1 ] != 0) {
				return true;
			}if(column != board.length -2 && row != board.length - 2) {
				if(board[row + 2][column + 2] != 0) {
					return true;
				}
			}
		}
		//Check rightup
		if(column != board.length - 1 && row != 0) {
			if(board[row - 1 ][column + 1 ] != 0) {
				return true;
			}if(column != board.length -2 && row != 1) {
				if(board[row - 2][column + 2] != 0) {
					return true;
				}
			}
		}
		return false;
	}
}


