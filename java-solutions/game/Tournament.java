package game;

import java.util.ArrayList;

public class Tournament {
    private final boolean log;
    private final ArrayList<Player> players;
    private final int countplayer;
    private final ArrayList<Integer> pointsPlayer;

    public Tournament(final boolean log, ArrayList<Player> players,int countplayer) {
        this.log = log;
        this.players = players;
        this.countplayer = countplayer;
        pointsPlayer = new ArrayList<>();
        for (int i = 0; i < countplayer; i++) {
            pointsPlayer.add(0);
        }
    }

    public int play(Board board) {
        int num1,num2;
        for(int i = 0; i < countplayer;i++){
            for(int j = 0; j < countplayer; j++){
                num1 = i+1;
                num2 = j+1;
                if( i == j) {
                    continue;
                }
                System.out.println("Game Player "+ num1+ " VS  Player" +num2);
                final int result = playPlayer(board);
                if(result == 1){
                    pointsPlayer.set(i, pointsPlayer.get(i)+3);
                    System.out.println("In this game win Player "+ num1);
                }
                else if(result == 2){
                    pointsPlayer.set(j, pointsPlayer.get(j)+3);
                    System.out.println("In this game win Player "+ num2);
                }
                else{
                    pointsPlayer.set(i, pointsPlayer.get(i)+1);
                    pointsPlayer.set(j, pointsPlayer.get(j)+1);
                    System.out.println("DRAW!");
                }
                board.cleanBoard();
            }
        }
        System.out.println("Overall result");
        int winnerNumber = 0;
        int winnerPoint = 0;

        for(int i = 0; i < countplayer;i++){
            int number = i+1;
            System.out.println("Player "+ number+" scored "+pointsPlayer.get(i)+ " points");
            if(winnerPoint < pointsPlayer.get(i)){
                winnerPoint = pointsPlayer.get(i);
                winnerNumber = number;
            }
        }
        System.out.println("WINNER: Player " + winnerNumber + " has " + winnerPoint + "points");
         return 0;

    }
    public int playPlayer(Board board) {

        while (true) {
            final int result1 = move(board, players.get(0), 1,0);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, players.get(1), 2,0);
            if (result2 != -1) {
                return result2;
            }
        }

    }

    private int move(final Board board, final Player player, final int no,final int bonus) {
        final Move move = player.move(new PositionPlayer(board.getPosition()), board.getCell());
        final Result result = board.makeMove(move,0);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else if((result == Result.BONUS) && (bonus ==0)){
            return move(board, player,no,1);
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
