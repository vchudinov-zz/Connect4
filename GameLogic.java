
public class GameLogic implements IGameLogic {
    private int x = 0;
    private int y = 0;
    private int playerID;
    
    public GameLogic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        this.x = x;
        this.y = y;
        this.playerID = playerID;
        //TODO Write your implementation for this method
    }
	
    public Winner gameFinished() {
        if (true){
            return Winner.PLAYER1;}
        else if(false){
            return Winner.PLAYER2;}
        else{
            return Winner.NOT_FINISHED;}
    }

    public boolean checkHorizontal(int[][] board, int playerID){
        for (int i = 0; i< board.length; i++){
            int score = 0;
            boolean flag = false;
            for(int j = 0; j < board[0].length;j++)
            {   if (board[i][j] == playerID)
                {   flag = true;
                    score +=1;
                    if (score == 4){
                        return true;
                    }
                }
                else{
                flag  = false;
                score = 0;
                }
            }
        }
        return false;
    }

    public boolean checkvertical(int[][] board, int playerID){
        for(int j = 0; j< board[0].length; j++){
            int score = 0;
            boolean flag = false;
            for(int i = 0; i < board.length;i++)
            {   if (board[i][j] == playerID){
                flag = true;}

                if(flag)
                {  score +=1;}

                if (score == 4){
                    return true;
                }

                else{
                    flag  = false;
                    score = 0;
                }
            }
        }
        return false;
    }



    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method	
    }

    public int decideNextMove() {
        //TODO Write your implementation for this method
        return 0;
    }

}
