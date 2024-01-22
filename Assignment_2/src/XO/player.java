/*Amit Sherman 209284017
Stav Zysblatt 313434748 */
package XO;

public abstract class player implements Runnable{
    Turn turn;
    public player (Turn p) {
        this.turn =p;
    }
}

