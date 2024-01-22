/*Amit Sherman 209284017
Stav Zysblatt 313434748 */
package XO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("In order to initialize user vs comp version insert 1.");
        System.out.println("In order to initialize user vs comp insert any other integer number.");

        Scanner s = new Scanner(System.in);
        int typeOfGame = s.nextInt();
        if (typeOfGame == 1) {
            System.out.println("the bored index are from 0 - 4\tinsert index SPACE index");
            UserGame newGame = new UserGame();
            newGame.StartGame();
        } else {
            SelfGame selfGame = new SelfGame();
            selfGame.StartGame();
        }
    }
}