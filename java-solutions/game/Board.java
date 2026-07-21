package game;

public interface Board {
    BoardPosition getPosition();
    Cell getCell();
    Result makeMove(Move move,int bonus);
    void  cleanBoard();
    int getM();
    int getN();
}