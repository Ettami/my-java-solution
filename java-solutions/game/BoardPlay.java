package game;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public abstract class BoardPlay implements Board, BoardPosition{
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private Cell[][] cells;
    private Cell turn;
    private int m;
    private int n;
    private int k;

    public BoardPlay() {
        final Scanner in = new Scanner(System.in);
        int kindGame = kindPlay();
        int stupcounter = 0;
        if(kindGame == 1){
            this.m = -1;
            this.n = -1;
            this.k = -1;
        }
        else
        {
            this.m = 11;
            this.n = 11;
            while(k <= 0 || ((k > m) & (k> n))){
                if(stupcounter != 0){
                    System.out.println("Error! Values cannot be less than or equal to 0! And values don't be a  string type! Please re-enter!");
                }
                stupcounter++;
                System.out.println("Write the conditions for the game ");

                try {
                    System.out.println("Write K!");
                    k = in.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("ERROR! YOU NEED TO WRITE INTEGER NUMBER");
                    in.nextLine();
                    k = -1;
                }
                if(stupcounter > 15) {
                    throw new AssertionError("Incorrect data entry");
                }
            }
        }

        while (m <= 0 || n <= 0 || k <= 0 || ((k > m) & (k> n))){
            if(stupcounter != 0){
                System.out.println("Error! Values cannot be less than or equal to 0! And values don't be a  string type! Please re-enter!");
            }
            stupcounter++;
            System.out.println("Write the conditions for the game ");

            try {
                System.out.println("Write M!");
                m = in.nextInt();

                System.out.println("Write N!");
                n = in.nextInt();


                System.out.println("Write K!");
                k = in.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("ERROR! YOU NEED TO WRITE INTEGER NUMBER");
                in.nextLine();
                m = -1;
                n = -1;
                k = -1;
            }
            if(stupcounter > 15) {
                throw new AssertionError("Incorrect data entry");
            }
        }
        cleanBoard();

    }

    @Override
    public BoardPosition getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    protected abstract Integer kindPlay();

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    @Override
    public Result makeMove(final Move move,int bonus) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        if (check(move.getRow(), move.getColumn(),k)) {
            return Result.WIN;
        }
        if(check(move.getRow(), move.getColumn(),4) && (bonus == 0)){
            return Result.BONUS;
        }
        if (isDraw()) {
            return Result.DRAW;
        }
        if (check(move.getRow(), move.getColumn(),k)) {
            return Result.WIN;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private boolean check(int row,int col,int k){
        int kindGame = kindPlay();
        if(kindGame == 1)
            return checkMNK(row, col,k);
        else
            return checkHEX(row, col,k);
    }

    private boolean checkMNK(int row, int col,int kolv) {
        int leftCount = 0;
        int rightCount = 0;

        int c = col - 1;
        int steps = 0;
        while (c >= 0 && steps < kolv && cells[row][c] == turn) {
            leftCount++;
            c--;
            steps++;
        }

        c = col + 1;
        steps = 0;
        while (c < n && steps < kolv && cells[row][c] == turn) {
            rightCount++;
            c++;
            steps++;
        }

        if ((1 + leftCount + rightCount) >= kolv)
            return true;

        int upCount = 0;
        int downCount = 0;

        int r = row - 1;
        steps = 0;
        while (r >= 0 && steps < kolv && cells[r][col] == turn) {
            upCount++;
            r--;
            steps++;
        }

        r = row + 1;
        steps = 0;
        while (r < m && steps < kolv && cells[r][col] == turn) {
            downCount++;
            r++;
            steps++;
        }

        if ((1 + upCount + downCount) >= kolv)
            return true;

        int upLeftCount = 0;
        int downRightCount = 0;

        r = row - 1;
        c = col - 1;
        steps = 0;
        while (r >= 0 && c >= 0 && steps < kolv && cells[r][c] == turn) {
            upLeftCount++;
            r--;
            c--;
            steps++;
        }

        r = row + 1;
        c = col + 1;
        steps = 0;
        while (r < m && c < n && steps < kolv && cells[r][c] == turn) {
            downRightCount++;
            r++;
            c++;
            steps++;
        }

        if ((1 + upLeftCount + downRightCount) >= kolv)
            return true;

        int upRightCount = 0;
        int downLeftCount = 0;

        r = row - 1;
        c = col + 1;
        steps = 0;
        while (r >= 0 && c < n && steps < kolv && cells[r][c] == turn) {
            upRightCount++;
            r--;
            c++;
            steps++;
        }

        r = row + 1;
        c = col - 1;
        steps = 0;
        while (r < m && c >= 0 && steps < kolv && cells[r][c] == turn) {
            downLeftCount++;
            r++;
            c--;
            steps++;
        }

        return (1 + upRightCount + downLeftCount) >= kolv;
    }

    private boolean checkHEX(int row, int col,int kolv) {
        int leftCount = 0;
        int rightCount = 0;

        int c = col - 1;
        int steps = 0;
        while (c >= 0 && steps < kolv && cells[row][c] == turn) {
            leftCount++;
            c--;
            steps++;
        }

        c = col + 1;
        steps = 0;
        while (c < n && steps < kolv && cells[row][c] == turn) {
            rightCount++;
            c++;
            steps++;
        }

        if ((1 + leftCount + rightCount) >= kolv)
            return true;

        int upCount = 0;
        int downCount = 0;

        int r = row - 1;
        steps = 0;
        while (r >= 0 && steps < kolv && cells[r][col] == turn) {
            upCount++;
            r--;
            steps++;
        }

        r = row + 1;
        steps = 0;
        while (r < m && steps < kolv && cells[r][col] == turn) {
            downCount++;
            r++;
            steps++;
        }

        if ((1 + upCount + downCount) >= kolv)
            return true;


        int upRightCount = 0;
        int downLeftCount = 0;

        r = row - 1;
        c = col + 1;
        steps = 0;
        while (r >= 0 && c < n && steps < kolv && cells[r][c] == turn) {
            upRightCount++;
            r--;
            c++;
            steps++;
        }

        r = row + 1;
        c = col - 1;
        steps = 0;
        while (r < m && c >= 0 && steps < kolv && cells[r][c] == turn) {
            downLeftCount++;
            r++;
            c--;
            steps++;
        }

        return (1 + upRightCount + downLeftCount) >= kolv;
    }

    private boolean isDraw() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell == Cell.E) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        sb.append("  ");
        for (int c = 0; c < n; c++) {
            sb.append(c);
            sb.append(" ");
        }

        for (int r = 0; r < m; r++) {
            sb.append("\n");
            if(r <= 9)
                sb.append(r+ "  ");
            else
                sb.append(r+ " ");
            for (int c = 0; c < n; c++) {
                if(c >=10) {
                    sb.append(SYMBOLS.get(getCell(r, c)) + "  ");
                }
                else{
                    sb.append(SYMBOLS.get(getCell(r, c)) + " ");
                }
            }
        }
        return sb.toString();
    }
    public void cleanBoard(){
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        this.turn = Cell.X;
    }
}
