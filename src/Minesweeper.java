import java.util.Random;
import java.util.Scanner;

public class Minesweeper {

   private static int[][] board;
   private static char[][] mineDetector;
   private static char[][] gameboard;
   private static  int rows;
   private static int cols;
   private static int noOfopenedslots;
   private static int noofMines;

   // Constructor to develop a board with mines
   public Minesweeper(int r, int c, int mines) {
	   
	   rows = r;
	   cols = c;
	   noOfopenedslots = 0;
	   noofMines = mines;
	   
       Random xIdx = new Random();
       Random yIdx = new Random();

       mineDetector = new char[rows][cols];
       board = new int[rows][cols];
       gameboard = new char[rows][cols];
       
       for(int i = 0;i < rows; i++) {
           for(int j = 0;j < cols; j++) {
        	   mineDetector[i][j] = '_';
        	   gameboard[i][j] = '_';
           }
       }
       
       for(int i = 0;i < noofMines; i++) {
           int rw = xIdx.nextInt(rows);
           int cl = yIdx.nextInt(cols);

           mineDetector[rw][cl] = '*';
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

   public static void printboard() {
	   
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
   
   public static void printgameboard() {
       for(int i = 0;i < rows; i++) {
           for(int j = 0;j < cols; j++) {
               System.out.print(gameboard[i][j]);
           }
           System.out.println();
       }
   }
   
   // Function to start the game till winner decided
   public static void run() {
	   try (Scanner input = new Scanner(System.in)) {
		   while(true) {
        	   String inputString = input.nextLine().trim();
        	   String[] ipArr = inputString.split(",");
        	   
        	   int xCoor = Integer.parseInt(ipArr[0]);
        	   int yCoor = Integer.parseInt(ipArr[1]);
        	   
        	   if(mineDetector[xCoor][yCoor] == '*') {
        		   lose();
        		   System.out.println();
        		   System.out.println("FINAL BOARD");
                   printboard();
        		   System.exit(0);
        	   }
        	   
        	   noOfopenedslots++;
        	   gameboard[xCoor][yCoor] = Character.forDigit(board[xCoor][yCoor], 10);
               if(hasWon()){
            	   win();
            	   System.exit(0);
               }
               printgameboard();
           }
	   } catch (Exception e) {
           e.printStackTrace();
       } 
   }
   
   public static void win() {
	   System.out.println("CONGRATS!! YOU HAVE WON THE GAME");
   }
   
   public static void lose() {
	   System.out.println("SORRY YOU LOST!! BETTER LUCK NEXT TIME");
   }
   
   // Function to see if user has won the game
   public static boolean hasWon() {
	   if(noOfopenedslots == ((rows*cols) - noofMines)) {
		   return true; 
	   } else {
		   return false;
	   } 
   }

   public static void main(String args[]) {
       Minesweeper game = new Minesweeper(3,3,1);
       run();
   }
}


