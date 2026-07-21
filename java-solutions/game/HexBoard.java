package game;

import java.util.Map;

public class HexBoard extends BoardPlay{
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    @Override
    protected Integer kindPlay() {
        return 0;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("     ");
        for (int c = 0; c < 11; c++) {
            sb.append(c);
            sb.append(" ");
        }

        for (int r = 0; r < 11; r++) {
            sb.append("\n");
            sb.append("  ");
            for(int i = 0; i < r; i++){
                sb.append(" ");
            }
            sb.append(r+ "  ");
            for (int c = 0; c < 11; c++) {
                if(c >=9) {
                    sb.append(SYMBOLS.get(getCell(r, c)) + "  ");
                }
                else{
                    sb.append(SYMBOLS.get(getCell(r, c)) + " ");
                }
            }
        }
        return sb.toString();
    }
}
