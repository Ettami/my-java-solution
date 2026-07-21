package game;

public class PositionPlayer implements Position{
    private final BoardPosition boardPosition;

    public PositionPlayer(BoardPosition boardPosition) {
        this.boardPosition = boardPosition;
    }

    @Override
    public boolean isValid(Move move) {
        return boardPosition.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return boardPosition.getCell(r,c);
    }

    @Override
    public int getM() {
        return boardPosition.getM();
    }

    @Override
    public int getN() {
        return boardPosition.getN();
    }
}
