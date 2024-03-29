/*Amit Sherman 209284017
Stav Zysblatt 313434748 */
package XO;

import java.util.Random;

public class selfPlayer extends player {

    boolean isUserGame;
    UserGame g1;
    SelfGame g2;

    public selfPlayer(Turn p, UserGame g1) {
        super(p);
        isUserGame = true;
        this.g1 = g1;
    }

    public selfPlayer(Turn p, SelfGame g2) {
        super(p);
        isUserGame = false;
        this.g2 = g2;
    }

    public void run() {
        if (isUserGame) {
            while (!g1.gameOver) {
                try {
                    synchronized (g1.lock) {
                        Thread.sleep(500);
                        if (g1.getTurn() == turn) {

                            if (g1.notFull()) {
                                System.out.println("Comp is playing");
                                cell[] freeCells = g1.getFreeCells();
                                Random r = new Random();
                                cell move = freeCells[r.nextInt(freeCells.length)];
                                g1.gameBoard[move.x][move.y] = turn;
                                g1.printBoard();
                                g1.checkIfWon();
                                g1.playerTurn = !g1.playerTurn;
                                g1.lock.notify();
                            } else {
                                System.out.println("Board is full");
                                g1.playerTurn = !g1.playerTurn;
                                g1.lock.notify();
                                if (g1.gameOver)
                                    break;
                            }
                        } else {
                            g1.lock.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            while (!g2.gameOver) {
                try {
                    synchronized (g2.lock) {
                        Thread.sleep(500);
                        if (g2.getTurn() == turn) {

                            if (g2.notFull()) {
                                System.out.println("Comp is playing");
                                cell[] freeCells = g2.getFreeCells();
                                Random r = new Random();
                                cell move = freeCells[r.nextInt(freeCells.length)];
                                g2.gameBoard[move.x][move.y] = turn;
                                g2.printBoard();
                                g2.checkIfWon();
                                g2.playerTurn = !g2.playerTurn;
                                g2.lock.notify();
                            } else {
                                System.out.println("Board is full");
                                g2.playerTurn = !g2.playerTurn;
                                g2.lock.notify();
                                if (g2.gameOver) {

                                    break;
                                }
                            }
                        } else {
                            g2.lock.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
