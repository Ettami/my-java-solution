package game;

public interface BoardPosition {
    boolean isValid(Move move);
    Cell getCell(int r, int c);
    int getM();
    int getN();
}
