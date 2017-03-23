import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Board {
 
    List<Point> availableCells;
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[3][3];
    
    public static final int Player_O = 1; //Computer
    public static final int Player_X = 2; //User
    public static final int No_Player = 0;
    public Point Computer_move;
    
    public Board() {
    }

    public boolean isGameOver() {
        //Game is over if someone has won, or board is full (draw)
        return (hasPlayerWon(Player_X) || hasPlayerWon(Player_O) || getAvailableStates().isEmpty());
    }

    public boolean hasPlayerWon(int player) {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == player) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == player)) {
            //System.out.println("X Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == player)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == player))) {
                // System.out.println("X Row or Column win");
                return true;
            }
        }
        return false;
    }



    public List<Point> getAvailableStates() {
        availableCells = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    availableCells.add(new Point(i, j));
                }
            }
        }
        return availableCells;
    }

    public boolean placeAMove(Point point, int player) {
    	if(board[point.x][point.y]!= No_Player)
    		return false;
        board[point.x][point.y] = player;   //player = 1 for X (Computer), 2 for O (User)
        return true;
    }

    public void displayBoard() {
        System.out.println();
          int num = 1;
         // String value = Integer.toString(num);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
            	String value =  Integer.toString(num);
           	 //System.out.print(value+ " ");
            
            	 if(board[i][j] == Player_X)
                 	value = "X";
                 else if(board[i][j] == Player_O)
                	 value = "O";
            	 System.out.print(value + " ");
            	    	 num++;
            }
            
            System.out.println();
        }
    }


    List<PointsAndScores> rootsChildrenScores;
 
      public int minimax(int depth, int turn) {

        if (hasPlayerWon(Player_X)) return 1; // User (Select lowest) +1
        if (hasPlayerWon(Player_O)) return -1; // Computer (Select highest) -1

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        List<Point> statesAvailable = getAvailableStates();
        if (statesAvailable.isEmpty()) return 0;  

        List<Integer> scores = new ArrayList<>(); 

        for (int i = 0; i < statesAvailable.size(); ++i) {
            Point point = statesAvailable.get(i);  

            if (turn == Player_O)  //O's turn should select the lowest from below minimax() call
            {
              //  placeAMove(point, Player_O); 
                int currentScore = minimax(depth - 1, Player_X);
                min = Math.min(currentScore, min);
                scores.add(currentScore);

                if (depth == 0) 
                {
                    //rootsChildrenScores.add(new PointsAndScores(currentScore, point));
                    System.out.println("Computer scores for this position" + point + "=" + currentScore);
                }
                if(currentScore <= 0)
                	if(depth == 0)
                		Computer_move =point; 
                
                if(currentScore == -1){
                	board[point.x][point.y] = No_Player;
                	break;}
               if(i == statesAvailable.size()-1 && min > 0)
                {
                	if(depth == 0)
                		Computer_move = point;
                }
               
            } 
            else if (turn == Player_X) //X's turn select the highest from below minimax() call
            {
               // placeAMove(point, Player_X); 
                int currentScore = minimax(depth - 1, Player_O);
                max = Math.max(currentScore, max);
                scores.add(currentScore);
               
                if( max == 1)
                	{
                		board[point.x][point.y] = No_Player;
                		break;
                	}
            }
            board[point.x][point.y] = No_Player; //Reset this point
        }
        return turn == Player_X ? max : min;
    }
}

