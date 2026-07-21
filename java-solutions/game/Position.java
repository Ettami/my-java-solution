package game;

public interface Position extends BoardPosition {
    boolean isValid(Move move);
    Cell getCell(int r, int c);
    int getM();
    int getN();
}
