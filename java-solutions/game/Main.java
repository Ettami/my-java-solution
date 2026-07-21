package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game(false, new RandomPlayer(), new HumanPlayer());
        int result;
        int stupplay = 0,ifgame = 0,countplayers =0;
        String kindOfGame;
        System.out.println("Hi! What game do you want to play?");
        while(ifgame == 0 && stupplay <10) {
            System.out.println("1. Game MNK");
            System.out.println("2. Game MNK Tournament Version");
            System.out.println("3. Game Gex");
            Scanner in = new Scanner(System.in);
            try {
                kindOfGame = in.next();
                if (kindOfGame.equals("1")) {
                    ifgame = 1;
                    do {
                        result = game.play(new MnkBoard());
                        System.out.println("Game result: " + result);
                    } while (result != 0);
                } else if (kindOfGame.equals("2")) {
                    ifgame = 1;
                    System.out.println("Enter the number of players ");
                    countplayers = in.nextInt();
                    ArrayList<Player> players = new ArrayList<>(countplayers);
                    for(int i = 0; i < countplayers; i++){
                        players.add(new HumanPlayer());
                    }
                    final Tournament tournament = new Tournament(false, players, countplayers);
                    result = tournament.play(new MnkBoard());
                }
                else if (kindOfGame.equals("3")) {
                    ifgame = 1;
                    do {
                        result = game.play(new HexBoard());
                        System.out.println("Game result: " + result);
                    } while (result != 0);
                }
                else{
                    System.out.println("ERROR CHOSE NUMBER OF GAME!");
                    stupplay++;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR! YOU NEED TO WRITE INTEGER NUMBER");
                in.nextLine();
            }
            if(stupplay > 15) {
                throw new AssertionError("Incorrect data entry");
            }

        }

    }
}
