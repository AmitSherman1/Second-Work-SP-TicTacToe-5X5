/*Amit Sherman 209284017
Stav Zysblatt 313434748 */
package XO;

public class UserGame extends Game {

    boolean playerTurn;

    public UserGame() {
        gameBoard = new Turn[5][5];
        playerTurn = true;
    }

    public synchronized void checkIfWon() {
        // Check rows
        for (int i = 0; i < 5; i++)
            if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] &&
                    gameBoard[i][0] == gameBoard[i][3] && gameBoard[i][0] != null ||gameBoard[i][1] == gameBoard[i][2] &&
                    gameBoard[i][1] == gameBoard[i][3] && gameBoard[i][1] == gameBoard[i][4] &&
                    gameBoard[i][1] != null) {
                System.out.println(gameBoard[i][0] + " WON!");
                gameOver = true;
            }

        // Check columns
        for (int i = 0; i < 5; i++)
            if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] &&
                    gameBoard[i][0] == gameBoard[i][3] && gameBoard[i][0] != null ||gameBoard[i][1] == gameBoard[i][2] &&
                    gameBoard[i][1] == gameBoard[i][3] && gameBoard[i][1] == gameBoard[i][4] &&
                    gameBoard[i][1] != null) {
                System.out.println(gameBoard[0][i] + " WON!");
                gameOver = true;
            }

        // Check diagonals
        if (((gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] &&
                gameBoard[2][2] == gameBoard[3][3]) ||
                (gameBoard[0][4] == gameBoard[1][3] && gameBoard[1][3] == gameBoard[2][2] &&
                        gameBoard[2][2] == gameBoard[3][1]) ||
                (gameBoard[1][1] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[3][3] && gameBoard[3][3] == gameBoard [4][4])
                || gameBoard[1][3] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[3][1] && gameBoard[3][1] == gameBoard [4][0])
                && gameBoard[2][2] != null) {
            System.out.println(gameBoard[2][2] + " WON!");
            gameOver = true;
        }
    }

    public synchronized Turn getTurn() {
        if (playerTurn)
            return Turn.X;
        return Turn.O;
    }

    public synchronized cell[] getFreeCells() {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard[i][j] == null) {
                    count++;
                }
            }
        }
        cell[] emptyCells = new cell[count];
        int index =0;
        for(int i =0; i<5; i++){
            for(int j=0; j<5; j++){
                if(gameBoard[i][j] == null){
                    emptyCells[index++] = new cell(i,j);
                }
            }
        }
        return emptyCells;
    }
   public void StartGame(){
        UserPlayer user = new UserPlayer(Turn.X, this);
        selfPlayer ai = new selfPlayer(Turn.O, this);
        Thread userThread = new Thread(user);
        Thread aiThread = new Thread(ai);
        userThread.start();
        aiThread.start();
   }
}