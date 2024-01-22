/*Amit Sherman 209284017
Stav Zysblatt 313434748 */
package XO;

public abstract class Game {

    public Game() {
        gameBoard = new Turn[5][5];
        gameOver = false;
        lock= new Object();
    }

    public void gameBoard(){}
    protected Turn[][] gameBoard;
    public abstract Turn getTurn();
    public abstract cell[] getFreeCells();
    public boolean gameOver;
    public final Object lock;
    public synchronized void printBoard(){
        for (int i=0; i< gameBoard.length; i++){
            for(int j=0; j<gameBoard[0].length; j++){
                if(gameBoard[i][j] == null)
                    System.out.print(gameBoard[i][j]+"     ");
                else{
                    System.out.print(gameBoard[i][j]+"      ");
                }
            }
            System.out.println();
        }
    }
    public boolean notFull(){
        for (int i=0; i<gameBoard.length; i++){
            for (int j=0; j<gameBoard[0].length; j++){
                if (gameBoard[i][j] == null)
                    return true;
            }
        }
        gameOver=true;
        return false;
    }
}
