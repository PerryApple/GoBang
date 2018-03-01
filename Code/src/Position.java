public class Position implements Comparable<Position>{
	private int row;
	private int column;
	private int positionPoint;
	public Position(int row,int column,int positionPoint) {
		this.row = row;
		this.column = column;
		this.positionPoint = positionPoint;
	}
	public int getPoint() {
		return positionPoint;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	@Override
	public int compareTo(Position p) {
		// TODO Auto-generated method stub
		return ((this.positionPoint == p.getPoint()) ? 0 :((this.positionPoint < p.getPoint()) ? 1 : -1));
	}
	@Override
	public String toString() {
		return "(" + this.row + " , " + this.column + " , " + this.positionPoint + " )";
	}
}