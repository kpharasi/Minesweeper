import java.util.Random;

public class Minesweeper {

   private int[][] board;
   private char[][] mineDetector;
   private int rows;
   private int cols;

   // Constructor to develop a board with mines
   public Minesweeper(int rows, int cols, int noofMines) {
	   
	   this.rows = rows;
	   this.cols = cols;
	   
       Random xIdx = new Random();
       Random yIdx = new Random();

       mineDetector = new char[rows][cols];
       board = new int[rows][cols];
       
       for(int i = 0;i < rows; i++) {
           for(int j = 0;j < cols; j++) {
        	   mineDetector[i][j] = '_';
           }
       }
       
       for(int i = 0;i < noofMines; i++) {
           int r = xIdx.nextInt(rows);
           int c = yIdx.nextInt(cols);

           mineDetector[r][c] = '*';
       }

       for(int i = 0;i < rows; i++) {
           for(int j = 0;j < cols; j++) {
               if(mineDetector[i][j] == '*') {
                   for(int adjIdxX = i-1;adjIdxX <= i+1; adjIdxX++) {
                       if(!((adjIdxX >=0) && (adjIdxX < board.length))) {
                           continue;
                       }
                       for(int adjIdxY = j-1;adjIdxY <= j+1; adjIdxY++) {
                           if((adjIdxX == i && adjIdxY == j)|| !((adjIdxY >=0) && (adjIdxY < board[0].length))) {
                               continue;
                           } else {
                               board[adjIdxX][adjIdxY] += 1;
                           }
                       }
                   }
               }
           }
       }
      
   }

   public void printboard() {
	   
	   System.out.println("Board:");
       for(int i = 0;i < rows; i++) {
           for(int j = 0;j < cols; j++) {
               System.out.print(board[i][j]);
           }
           System.out.println();
       }
       System.out.println();
       
       
       System.out.println("Board with Mines:");
       for(int i = 0;i < rows; i++) {
           for(int j = 0;j < cols; j++) {
               System.out.print(mineDetector[i][j]);
           }
           System.out.println();
       }
       
   }

   public static void main(String args[]) {

       Minesweeper game = new Minesweeper(3,3,1);
       game.printboard();
   }
}


