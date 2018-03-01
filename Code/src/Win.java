public class Win {
    public static int haveAWin(int[][] board,int m){
        //横着检查
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length-m+1;j++){
                boolean finished = true;
                for(int k=1;k<m;k++){
                    if(board[i][j+k]!=board[i][j]||board[i][j]==0) finished=false;
                }
                if(finished){
                    if(board[i][j]==1) return 1;// 1 win
                    else return -1; //-1 win
                }
            }
        }
        //竖着检查
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length-m+1;j++) {
                boolean finished = true;
                for (int k = 1; k < m; k++) {
                    if (board[j+k][i] != board[j][i]||board[j][i]==0) finished = false;
                }
                if(finished){
                    if(board[j][i]==1) return 1;// 1 win
                    else return -1; //-1 win
                }
            }
        }
        //斜着检查1
        for(int i=0;i<board.length-m+1;i++){
            for(int j=0;j<board.length-m+1;j++) {
                boolean finished = true;
                for (int k = 1; k < m; k++) {
                    if (board[i+k][j+k] != board[i][j]||board[i][j]==0) finished = false;
                }
                if(finished){
                    if(board[i][j]==1) return 1;// 1 win
                    else return -1; //-1 win
                }
            }
        }
        //斜着检查2
        for(int i=0;i<board.length-m+1;i++){
            for(int j=board.length-1;j > m-2;j--) {
                boolean finished = true;
                for (int k = 1; k < m; k++) {
                    if (board[i+k][j-k] != board[i][j]||board[i][j]==0) finished = false;
                }
                if(finished){
                    if(board[i][j]==1) return 1;// 1 win
                    else return -1; //-1 win
                }
            }
        }
        return 0; //还没结束
    }
}
