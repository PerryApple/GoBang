import java.util.ArrayList;
import java.util.List;

/*evalueate the game, give a score to a certain situation
* ONE:   10
* TWO:   100
* THREE：1000
* FOUR:  10000
* FIVE:  100000
* SIX:   1000000
* SEVEN: 10000000
* EIGHT: 100000000
*  BLOCK_ONE:  1
*  BLOCK_TWO:  10
*  BLOCK_THREE:100
*  BLOCK_FOUR: 1000
*  BLOCK_FIVE: 10000
*  BLOCK_SIX:  100000
*  BLOCK_SEVEN:1000000
*  BLOCK_EIGHT:10000000
* */
public class Evaluate {
    int score = 0; //得分
    boolean blocked; //记录是否是死棋
    int numOfBlank; //记录中间空格数
    int index;  //  记录当前是哪个棋子 -1 or 1
    int count; //数棋子的数量
    int empty; //记录两边空位
    //int[][] board = {{-1,0,1,1,-1,-1,1,-1,0,0,0,1,0,0,1}} ;
    	private void evaluateRow(int[] board,int m) {
        for (int j = 0; j < board.length;j++) {
            if(board[j]==0) continue;
            else{
                //初始化
                blocked=false;
                index = board[j];
                numOfBlank = 0;
                count = 0;
                empty = 0;
                if(j==0||(board[j-1]!=index&&board[j-1]!=0)){
                    blocked = true;
                }else{
                    int pre = j-1;
                    //计算前面的空格数量
                    while(pre>=0&&(board[pre]==0||board[pre]==index)&&(empty+numOfBlank+count)<m){
                        empty++;
                        pre--;
                    }
                }
                for(;j<board.length;j++){
                    if (board[j] == 0) {
                        numOfBlank++;
                    }
                    if(board[j]!=index && board[j]!=0){
                        //这种情况是碰到不一样的棋子而输出因此检查前面一个是否为空位即可
                        if(board[j-1]!=0){
                            //前面一个不是空位
                            if(numOfBlank+empty+count>=m){
                                score += index * (int)Math.pow(10,count-1);
                            }
                            j--;
                            break;
                        }else
                        if(board[j-1]==0){
                            if(numOfBlank+empty+count>=m) {
                                if (blocked) score += index * (int) Math.pow(10, count - 1) ;
                                else score += index * (int) Math.pow(10, count) ;
                            }
                            j--;
                            break;
                        }
                    }else if(numOfBlank == 2) {
                        //因为是遇到两个空格停下来的，检查后面的空格数量
                        int aft = j + 1;
                        while (aft < board.length && (board[aft] == 0||board[aft] == index) && (empty + numOfBlank + count) < m) {
                            empty++;
                            aft++;
                        }
                        if (empty + numOfBlank + count >= m) {
                            if(blocked)  score += index * (int) Math.pow(10, count - 1) ;
                            else score += index * (int) Math.pow(10, count) ;
                        }
                        j--;
                        break;
                    }else if(j==board.length-1){
                        //到一行的最后判断是否要输出
                        if(board[j]!=0){
                            //最后一个不是0，要输出且是blocked
                            count++;
                            if(numOfBlank+empty+count>=m) {
                            		score += index * (int) Math.pow(10, count - 1) ;
                            }
                        }else if(board[j-1]!=0){
                            //最后一个是0，但倒数第二个不是0  eg:"01110"
                            empty++;
                            if(empty+numOfBlank+count>=m){
                                if(blocked)  score += index * (int) Math.pow(10, count - 1) ;
                                else score += score += index * (int) Math.pow(10, count) ;
                            }
                        }
                    } else if(board[j]==index){
                        count++;
                    }
                }
            }
        }
    }
    // i 是第几列
    private void evaluateColum(int i,int[][] board,int m){
        for (int j = 0; j < board.length;j++) {
            if(board[j][i]==0) continue;
            else{
                //初始化
                blocked=false;
                index = board[j][i];
                numOfBlank = 0;
                count = 0;
                empty = 0;
                if(j==0||(board[j-1][i]!=index&&board[j-1][i]!=0)){
                    blocked = true;
                }else{
                    int pre = j-1;
                    //计算前面的空格数量
                    while(pre>=0&&(board[pre][i]==0||board[pre][i]==index)&&(empty+numOfBlank+count)<m){
                        empty++;
                        pre--;
                    }
                }
                for(;j<board.length;j++){
                    if (board[j][i] == 0) {
                        numOfBlank++;
                    }
                    if(board[j][i]!=index && board[j][i]!=0){
                        //这种情况是碰到不一样的棋子而输出因此检查前面一个是否为空位即可
                        if(board[j-1][i]!=0){
                            //前面一个不是空位
                            if(numOfBlank+empty+count>=m){
                            	score += index * (int) Math.pow(10, count - 1) ;
                            }
                            j--;
                            break;
                        }else
                        if(board[j-1][i]==0){
                            if(numOfBlank+empty+count>=m) {
                                if (blocked) score += index * (int) Math.pow(10, count - 1) ;
                                else score += index * (int) Math.pow(10, count) ;
                            }
                            j--;
                            break;
                        }
                    }else if(numOfBlank == 2) {
                        //因为是遇到两个空格停下来的，检查后面的空格数量
                        int aft = j + 1;
                        while (aft < board.length && (board[aft][i] == 0||board[aft][i] == index) && (empty + numOfBlank + count) < m) {
                            empty++;
                            aft++;
                        }
                        if (empty + numOfBlank + count >= m) {
                            if(blocked)  score += index * (int) Math.pow(10, count - 1) ;
                            else score += index * (int) Math.pow(10, count) ;
                        }
                        j--;
                        break;
                    }else if(j==board.length-1){
                        //到一行的最后判断是否要输出
                        if(board[j][i]!=0){
                            //最后一个不是0，要输出且是blocked
                            count++;
                            if(numOfBlank+empty+count>=m) {
                            	score += index * (int) Math.pow(10, count - 1) ;
                            }
                        }else if(board[j-1][i]!=0){
                            //最后一个是0，但倒数第二个不是0  eg:"01110"
                            empty++;
                            if(empty+numOfBlank+count>=m){
                                if(blocked)  score += index * (int) Math.pow(10, count - 1) ;
                                else score += index * (int) Math.pow(10, count) ;
                            }
                        }
                    } else if(board[j][i]==index){
                        count++;
                    }
                }
            }
        }
    }

    //m是几子琪
    public int evaluateGame(int[][] board,int m){
//    		int winStatus = Win.haveAWin(board, m);
//    		if(winStatus!=0) {
//    			return winStatus * ((int)Math.pow(10, m));
//    		}
        //横着算分
    		score = 0;
        for(int i=0;i<board.length;i++){
            evaluateRow(board[i],m);
        }
        //竖着算分
        for(int i=0;i<board[0].length;i++){
            evaluateColum(i,board,m);
        }
        //斜着评分1
        for(int i=m-1;i<board.length;i++){
            List<Integer> rowlist = new ArrayList<>();
            int j = board.length-1,r = i;
            while(r>=0){
                rowlist.add(board[r][j]);
                r--;
                j--;
            }
            int [] row = new int[rowlist.size()];
            int p=0;
            for(int e:rowlist){
                row[p++]=e;
            }
            evaluateRow(row,m);
            r = i;j = board.length-1 ;
            rowlist = new ArrayList<>();
            while(r>=0&&r!=board.length-1){
                rowlist.add(board[j][r]);
                r--;
                j--;
            }
            p = 0;
            row = new int[rowlist.size()];
            for(int e:rowlist){
                row[p++]=e;
            }
            evaluateRow(row,m);
        }

        //斜着评分2
        for(int i=m-1;i<board.length;i++){
            int j=0;
            int r=i;
            List<Integer> rowlist = new ArrayList<>();
            while(r>=0){
                rowlist.add(board[r][j]);
                r--;
                j++;
            }
            int [] row = new int[rowlist.size()];
            int p=0;
            for(int e:rowlist){
                row[p++]=e;
            }
            evaluateRow(row,m);
        }
        for(int i=1;i<=board.length-m;i++){
            int j = board.length-1;
            int r = i;
            List<Integer> rowlist = new ArrayList<>();
            while(r<=board.length-1){
                rowlist.add(board[r][j]);
                r++;
                j--;
            }
            int [] row = new int[rowlist.size()];
            int p=0;
            for(int e:rowlist){
                row[p++]=e;
            }
            evaluateRow(row,m);
        }
        return score;
    }


    //初始化evaluate函数的参数
   public void initialize(int i, int j){
       blocked = false;
       numOfBlank = 0;
       empty=0;
       count = 1;
   }

//   public static void main(String[] arg){
//       evaluate test = new evaluate();
//       int[][] board = {{0,0,0,0},{0,1,0,0},{0,0,0,0},{0,0,0,0}};
//       int s = test.evaluateGame(1,board,2);
//       System.out.println(s);
//   }
}
