package in.ineuron.in;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
class Guesser {
int guessNum;
int guessNum() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Guesser, kindly guess the number:");

    while (true) {
        try {
            guessNum = scan.nextInt();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scan.next(); // Clear the input buffer
        }
    }

    return guessNum;
}

int generateRandomNum(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min + 1) + min;
}
}
class Player {
String name;
int guessNum;
Player(String name) {
    this.name = name;
}

int guessNum() {
    Scanner scan = new Scanner(System.in);
    System.out.println(name + ", kindly guess the number:");

    while (true) {
        try {
            guessNum = scan.nextInt();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scan.next(); // Clear the input buffer
        }
    }

    return guessNum;
}
}
class Umpire {
Guesser guesser;
Player[] players;
int numFromGuesser;
int[] numFromPlayers;
int[] scores;
Umpire(int numPlayers) {
    guesser = new Guesser();
    players = new Player[numPlayers];
    numFromPlayers = new int[numPlayers];
    scores = new int[numPlayers];
}

void collectNumFromGuesser() {
    numFromGuesser = guesser.generateRandomNum(1, 100);
    System.out.println("Guesser's number: " + numFromGuesser);
}

void collectNumFromPlayers() {
    for (int i = 0; i < players.length; i++) {
        numFromPlayers[i] = players[i].guessNum();
    }
}

void compare() {
    for (int i = 0; i < players.length; i++) {
        if (numFromGuesser == numFromPlayers[i]) {
            System.out.println(players[i].name + " won the round!");
            scores[i]++;
        } else {
            System.out.println(players[i].name + " lost the round.");
        }
    }
}

void playGame() {
    Scanner scan = new Scanner(System.in);
    boolean playAgain = true;

    while (playAgain) {
        collectNumFromGuesser();
        collectNumFromPlayers();
        compare();

        System.out.println("Scores:");
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].name + ": " + scores[i]);
        }

        System.out.println("Do you want to play again? (y/n)");
        String choice = scan.nextLine();

        if (!choice.equalsIgnoreCase("y")) {
            playAgain = false;
        }
    }
}
}

public class LaunchGame {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of players:");
		int numPlayers = scan.nextInt();
		scan.nextLine(); // Clear the input buffer
	    Umpire umpire = new Umpire(numPlayers);

	    for (int i = 0; i < numPlayers; i++) {
	        System.out.println("Enter name for Player " + (i + 1) + ":");
	        String name = scan.nextLine();
	        umpire.players[i] = new Player(name);

    }
	    umpire.playGame();
}
}
