import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';
    public static final char USER_MARK = 'X';
    public static final char BOT_MARK = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean isBoardEmpty = false;
        int winner;
        char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        log.info("Hi. Welcome to the game. Enjoy!");

        while (true) {
            printBoard(board);

            if (!isBoardEmpty) {
                prepareBoard(board);
                isBoardEmpty = true;
            }

            log.info("Your move!");
            userMove(scanner, board);
            botMove(board, random);

            winner = checkGameWinner(board);

            if (!hasEmptyCells(board)) {
                winner = 3;
            }

            if (winner != 0) {
                printResult(winner);
                break;
            }
        }
        scanner.close();
    }

    private static void printBoard(char[] board) {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private static void prepareBoard(char[] array) {
        Arrays.fill(array, EMPTY_CELL);
    }

    private static void userMove(Scanner scanner, char[] board) {
        while (true) {
            try {
                int move = scanner.nextInt();
                if (move > 0 && move <= BOARD_SIZE) {
                    if (board[move - 1] == USER_MARK || board[move - 1] == BOT_MARK)
                        log.info("That one is already in use. Enter another!");
                    else {
                        board[move - 1] = USER_MARK;
                        break;
                    }
                } else {
                    log.warn("Invalid input. Enter again!");
                }
            } catch (InputMismatchException _) {
                log.warn("Should be numbers from 1 to " + BOARD_SIZE + "!");
                scanner.nextLine();
            }
        }
    }

    private static void botMove(char[] board, Random random) {
        while (true) {
            int move = random.nextInt(9) + 1;
            if (board[move - 1] != USER_MARK && board[move - 1] != BOT_MARK) {
                board[move - 1] = 'O';
                break;
            }
        }
    }

    private static byte checkGameWinner(char[] board) {
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combination : winCombinations) {
            char a = board[combination[0]];
            char b = board[combination[1]];
            char c = board[combination[2]];

            if (a == b && b == c) {
                if (a == 'X') return 1;
                if (a == 'O') return 2;
            }
        }
        return 0;
    }

    private static boolean hasEmptyCells(char[] board) {
        for (char cell : board) {
            if (cell != USER_MARK && cell != BOT_MARK) {
                return true;
            }
        }
        return false;
    }

    private static void printResult(int winner) {
        switch (winner) {
            case 1 -> log.info("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            case 2 -> log.info("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            case 3 -> log.info("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            default -> throw new RuntimeException("Сталася помилка");
        }
    }
}