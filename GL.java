import java.util.ArrayList;
import java.util.Arrays;

public class GL implements IGameLogic {
    private int xMax = 0;
    private int yMax = 0;
    private int self; // own player ID
    private int[][] gameBoard;  // size [x][y]
    private int[] next; // next cell up for placement in each column
    private ArrayList<int[][]> winRows = new ArrayList<int[][]>(); // size [2][4][rows]
    private int numWinrows;
    private float maxScore;
    
   
    public GL() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        gameBoard = new int[x][y];
        next = new int[x];
        xMax = x;
        yMax = y;
        self = playerID;
        
        //Horizontal winRows: (x-3)*y total (24)
        for(y = 0; y < yMax; y++) {
            for(x = 0; x < xMax-3; x++) {
                int[] c1 = {x,y};
                int[] c2 = {x+1,y};
                int[] c3 = {x+2,y};
                int[] c4 = {x+3,y};
                int[][] row = {c1,c2,c3,c4};
                winRows.add(row);
                //System.out.println(x);
        }}
        //System.out.println(winRows.size());
       
        //Vertical winRows: (y-3)*x total (21)
        for(y = 0; y < yMax-3; y++) {
            for(x = 0; x < xMax; x++) {
                int[] c1 = {x,y};
                int[] c2 = {x,y+1};
                int[] c3 = {x,y+2};
                int[] c4 = {x,y+3};
                int[][] row = {c1,c2,c3,c4};
                winRows.add(row);
                //System.out.println(x);
        }}
        //System.out.println(winRows.size());
        
        //Diagonal upwards winRows: (x-3)*(y-3) total (12)
        for(y = 0; y < yMax-3; y++) {
            for(x = 0; x < xMax-3; x++) {
                int[] c1 = {x,y};
                int[] c2 = {x+1,y+1};
                int[] c3 = {x+2,y+2};
                int[] c4 = {x+3,y+3};
                int[][] row = {c1,c2,c3,c4};
                winRows.add(row);
                //System.out.println(x);
        }}
        //System.out.println(winRows.size());
        
        //Diagonal downwards winRows: (x-3)*(y-3) total (12)
        for(y = 3; y < yMax; y++) {
            for(x = 0; x < xMax-3; x++) {
                int[] c1 = {x,y};
                int[] c2 = {x+1,y-1};
                int[] c3 = {x+2,y-2};
                int[] c4 = {x+3,y-3};
                int[][] row = {c1,c2,c3,c4};
                winRows.add(row);
                //System.out.println(x);
        }}
        //System.out.println(winRows.size());
        numWinrows = winRows.size();
        maxScore = numWinrows*4; //Trivial upper bound on score. 3 pieces in every winRow and no options blocked - this is clearly better than achievable.
    }
	
    public Winner gameFinished() {
        //TODO Write your implementation for this method
        return Winner.NOT_FINISHED;
    }

    public void insertCoin(int column, int playerID) {
        gameBoard[column][next[column]] = playerID;
        next[column]++; //next is an array that for each column holds the height of the next placement
        boardVal(gameBoard, next);
    }
    
    //Takes a gameBoard, a "next" and a move. Returns the gameBoard and "next" simulating the result of that move.
    //public {int[][], int[]} simState(int[][] board, int[] curNext, int column, int playerID) {
    public void simState(int column, int playerID, int[][] newBoard, int[] newNext) {
        newBoard[column][newNext[column]] = playerID;
        newNext[column]++;
    }
    
    public void revertState(int column, int[][] newBoard, int[] newNext) {
        newBoard[column][newNext[column]-1] = 0;
        newNext[column] = newNext[column] -1;
    }
    
    public float boardVal(int[][] board, int[] curNext) {
        float score = 0;
        int win = 0;
        //TODO: list of cells that would finish a 3-row + check for accessibility
        
        
        for(int i = 0; i < numWinrows; i++) {
            float max = 0;
            float min = 0;
            int[][] row = winRows.get(i);
            for(int j = 0; j < 4; j++) {
                if (board[row[j][0]][row[j][1]] != 0) {
                    if (board[row[j][0]][row[j][1]] == self) {
                        max++;}
                    else min++;
            }}
            // If the row is not yet blocked by opponent add 1 point + number of cells already full
            if (min == 0) score = score + (1+max);
            if (max == 0) score = score - (1+min);
                     
        }
        System.out.println(score);
        return score;
    }

    public int decideNextMove() {
        int bestCol = -1;
        float bestScore = -999999999;
        float score;
        
        int[][] newBoard = new int[xMax][yMax];
        int[] newNext = new int[xMax];
        
        int x,y;
        for(x = 0; x < xMax; x++) {
            for(y = 0; y < yMax; y++) {
                newBoard[x][y] = gameBoard[x][y];}
            newNext[x] = next[x];
        }
        for(x = 0; x < xMax; x++) {
            if (next[x] < yMax) {
                simState(x, self, newBoard, newNext);
                score = boardVal(newBoard, newNext);
                if (score > bestScore) {
                    bestScore = score;
                    bestCol = x;}
                revertState(x, newBoard, newNext);
        }}  
        
    
        //TODO Write your implementation for this method
        System.out.println(bestCol);
        return bestCol;
    }

}
