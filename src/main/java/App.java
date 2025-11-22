import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';
    private static final char USER_MARK = 'X';
    private static final char BOT_MARK = 'O';
    private static final Random RANDOM = new Random();
    private static final char[] START_BOARD = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int winner = 0;
        char[] board = prepareBoard();
        System.out.println("Hi. Welcome to the game.");
        System.out.println("You have next game board with possible moves!");
        printBoard(START_BOARD);
        System.out.println("Let's start the game! Enjoy!");

        while (winner == 0) {
            printBoard(board); // Show the current state of the game board

            System.out.println("Your move!");
            userMove(scanner, board); // User move

            winner = checkGameWinner(board);
            if (winner != 0) {
                break;
            }

            botMove(board); // Computer move

            winner = checkGameWinner(board);
        }
        scanner.close();
        printResult(winner);
    }

    private static void printBoard(char[] board) {
        System.out.printf("%n %c | %c | %c%n", board[0], board[1], board[2]);
        System.out.println("-----------");
        System.out.printf(" %c | %c | %c%n", board[3], board[4], board[5]);
        System.out.println("-----------");
        System.out.printf(" %c | %c | %c%n%n", board[6], board[7], board[8]);
    }

    private static char[] prepareBoard() {
        char[] board = new char[BOARD_SIZE];
        Arrays.fill(board, ' ');
        return board;
    }

    private static void userMove(Scanner scanner, char[] board) {
        while (true) {
            try {
                int move = scanner.nextInt();
                if (move > 0 && move <= BOARD_SIZE) {
                    if (board[move - 1] == USER_MARK || board[move - 1] == BOT_MARK)
                        System.out.println("That one is already in use. Enter another!");
                    else {
                        board[move - 1] = USER_MARK;
                        break;
                    }
                } else {
                    System.out.println("Invalid input. Enter again!");
                }
            } catch (InputMismatchException _) {
                System.out.println("Should be numbers from 1 to " + BOARD_SIZE + "!");
                scanner.nextLine();
            }
        }
    }

    private static void botMove(char[] board) {
        while (true) {
            int move = RANDOM.nextInt(9) + 1;
            if (board[move - 1] != USER_MARK && board[move - 1] != BOT_MARK) {
                board[move - 1] = BOT_MARK;
                break;
            }
        }
    }

    private static int checkGameWinner(char[] board) { // Check the winning combinations
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combination : winCombinations) {
            char a = board[combination[0]];
            char b = board[combination[1]];
            char c = board[combination[2]];

            if (a == b && b == c && a != EMPTY_CELL) {
                if (a == USER_MARK) return 1;
                if (a == BOT_MARK) return 2;
            }
        }

        boolean hasEmptyCells = false;
        for (char cell : board) {
            if (cell != USER_MARK && cell != BOT_MARK) {
                hasEmptyCells = true;
                break;
            }
        }
        if (!hasEmptyCells) {
            return 3;
        }
        return 0;
    }

    private static void printResult(int winner) {
        switch (winner) {
            case 1 -> System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            case 2 -> System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            case 3 -> System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            default -> throw new RuntimeException("Something wrong!");
        }
    }
}