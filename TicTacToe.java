

class Point {

    int x, y;
    public Point (int x, int y)
    {
    	this.x = x;
    	this.y = y;
    }

    public Point(int position) {
    	if(position > 0 && position <= 3)
    	{
    		this.x = 0;
    		this.y = position - 1;
    	}
    	else if(position > 3 && position <= 6)
    	{
    		this.x = 1;
    		this.y = position - 4;
    	}
    	else if(position > 6 && position <= 9)
    	{
    		this.x = 2;
    		this.y = position - 7;
    	}
        
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

class PointsAndScores {

    int score;
    Point point;

    PointsAndScores(int score, Point point) {
        this.score = score;
        this.point = point;
    }
}


public class TicTacToe {

    public static void main(String[] args) {
        Board b = new Board();
     
        b.displayBoard();

        System.out.println("What game would you like to play? 1) Tic-tac-toe. 2) Global Thermal Nuclear War.");
        int choice = b.scan.nextInt();
        if(!(choice == 1 || choice == 2))
        	{
        		System.out.println("Please enter a valid choice between 1 or 2 from next time! Now lets start with you. ");
           	}
       
        	if(choice==2)
    	{
    		System.out.println("Sorry, I cannot allow that. Let's play Tic-tac-toe. You start.");
    	}
        
        while (!b.isGameOver()) {
        	boolean moveOk = true;
        	do{
        		if(!moveOk)
        			System.out.println("Cell already filled ! Please make a vlid movement.");
        	
        		System.out.println("Your move: ");
           
        		Point userMove = new Point(b.scan.nextInt());
        		moveOk = b.placeAMove(userMove, Board.Player_X);
          
        	} while (!moveOk);
            
            b.displayBoard();
            if (b.isGameOver()) {
                break;
            } 
           
            b.minimax(0, Board.Player_O); //starts the game at depth 0 for computer
            System.out.println("Computer chose position :" + b.Computer_move);;
            b.placeAMove(b.Computer_move, Board.Player_O);
            b.displayBoard();
        }
        
     
        if (b.hasPlayerWon(Board.Player_O)) {
            System.out.println("Unfortunately, you lost!");
        } else if (b.hasPlayerWon(Board.Player_X)) {
            System.out.println("You win (Impossible)! There must be a bug in this program !");
        } else {
            System.out.println("It's a draw!");
        }
    }
}