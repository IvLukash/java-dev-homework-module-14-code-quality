import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';
    public static final char USER_MARK = 'X';
    public static final char BOT_MARK = 'O';

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        byte input;
        byte rand;
        boolean boxAvailable;
        byte winner = 0;
        char[] box = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        log.info("Enter box number to select. Enjoy!\n");
        boolean boxEmpty = false;
        while (true) {
            System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
            System.out.println("-----------");
            System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
            System.out.println("-----------");
            System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
            if(!boxEmpty){
                for(int i = 0; i < BOARD_SIZE; i++)
                    box[i] = EMPTY_CELL;
                boxEmpty = true;
            }

            if(winner == 1){
                log.info("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            } else if(winner == 2){
                log.info("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            } else if(winner == 3){
                log.info("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            }

            while (true) {
                input = scan.nextByte();
                if (input > 0 && input < 10) {
                    if (box[input - 1] == USER_MARK || box[input - 1] == BOT_MARK)
                        log.info("That one is already in use. Enter another.");
                    else {
                        box[input - 1] = USER_MARK;
                        break;
                    }
                }
                else
                    log.info("Invalid input. Enter again.");
            }

            if((box[0]=='X' && box[1]=='X' && box[2]=='X') || (box[3]=='X' && box[4]=='X' && box[5]=='X') || (box[6]=='X' && box[7]=='X' && box[8]=='X') ||
               (box[0]=='X' && box[3]=='X' && box[6]=='X') || (box[1]=='X' && box[4]=='X' && box[7]=='X') || (box[2]=='X' && box[5]=='X' && box[8]=='X') ||
               (box[0]=='X' && box[4]=='X' && box[8]=='X') || (box[2]=='X' && box[4]=='X' && box[6]=='X')){
                   winner = 1;
                   continue;
            }

            boxAvailable = false;
            for(int i=0; i<9; i++){
                if(box[i] != USER_MARK && box[i] != BOT_MARK){
                    boxAvailable = true;
                    break;
                }
            }

            if(!boxAvailable){
                winner = 3;
                continue;
            }

            while (true) {
                rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
                if (box[rand - 1] != USER_MARK && box[rand - 1] != BOT_MARK) {
                    box[rand - 1] = 'O';
                    break;
                }
            }

            if((box[0]=='O' && box[1]=='O' && box[2]=='O') || (box[3]=='O' && box[4]=='O' && box[5]=='O') || (box[6]=='O' && box[7]=='O' && box[8]=='O') ||
               (box[0]=='O' && box[3]=='O' && box[6]=='O') || (box[1]=='O' && box[4]=='O' && box[7]=='O') || (box[2]=='O' && box[5]=='O' && box[8]=='O') ||
               (box[0]=='O' && box[4]=='O' && box[8]=='O') || (box[2]=='O' && box[4]=='O' && box[6]=='O')){
                winner = 2;
            }
        }

    }
}