/*Amit Sherman 209284017
Stav Zysblatt 313434748 */
package XO;

import java.util.Scanner;

public class UserPlayer extends player {
    UserGame g1;

    public UserPlayer(Turn p, UserGame g1) {

        super(p);
        this.g1 = g1;
    }

    @Override
    public void run() {
        while (!g1.gameOver) {
            try {
                synchronized (g1.lock) {
                    if (g1.getTurn() == turn) {
                        if (g1.notFull()) {
                            Scanner s = new Scanner(System.in);
                            System.out.println("Please enter next move:");
                            cell move = new cell(s.nextInt(), s.nextInt());
                            for (cell c : g1.getFreeCells()) {
                                if (c.getX() == move.x && c.getY() == move.y) {
                                    g1.gameBoard[move.x][move.y] = turn;
                                    g1.printBoard();
                                    g1.checkIfWon();
                                    g1.playerTurn = !g1.playerTurn;
                                    g1.lock.notify();
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Board is full");
                            g1.playerTurn = !g1.playerTurn;
                            g1.lock.notify();
                            if (g1.gameOver) {
                                break;
                            }

                        }
                    } else {
                        g1.lock.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("User:" + Thread.currentThread().getId());
    }
}
