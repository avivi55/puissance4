import java.util.Arrays;

public class Board {
    private final int width;
    private final int height;
    public String[][] boardMatrix;

    int getAvailableIndex(int column) {
        for (int row = boardMatrix.length - 1; row >= 0; row--) {
            if (boardMatrix[row][column].equals(" ")) {
                return row;
            }
        }

        return -1;
    }

    public boolean placeToken(int column, String token){
        if (column < 0 || column > width-1)
            return true;

        int index = getAvailableIndex(column);

        if (index == -1)
            return true;

        boardMatrix[index][column] = token;

        return false;
    }

    boolean coordinatesOutOfBounds(int x, int y){
        return (x < 0 || x > width-1) || (y < 0 || y > height-1);
    }

    boolean isTokenWinning(String token){
        for (int y = boardMatrix.length-1 ; y >= 0; y--) {
            for (int x = 0; x < boardMatrix[y].length; x++) {
                if (countInDirection(token, x, y, Directions.VERTICAL) >= 4
                    || countInDirection(token, x, y, Directions.HORIZONTAL) >= 4
                    || countInDirection(token, x, y, Directions.DIAGONAL_LEFT) >= 4
                    || countInDirection(token, x, y, Directions.DIAGONAL_RIGHT) >= 4){

                    return true;

                }
            }
        }

        return false;
    }

    int countInDirection(String token, int x, int y, Directions direction){
        if (coordinatesOutOfBounds(x, y))
            return 0;

        if (!boardMatrix[y][x].equals(token))
            return 0;

        int count = 1;

        switch (direction) {
            case VERTICAL -> count += countInDirection(token, x, y - 1, direction);
            case HORIZONTAL -> count += countInDirection(token, x + 1, y, direction);
            case DIAGONAL_RIGHT -> count += countInDirection(token, x + 1, y - 1, direction);
            case DIAGONAL_LEFT -> count += countInDirection(token, x - 1, y - 1, direction);
        }

        return count;
    }

    Board(int width, int height){
        this.height = height;
        this.width = width;

        boardMatrix = new String[height][width];

        for (String[] row: boardMatrix) {
            Arrays.fill(row, " ");
        }
    }

    @Override
    public String toString(){
        String[][] stylizedBoard = new String[height][width];

        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                if (boardMatrix[i][j].equals("0")) {
                    stylizedBoard[i][j] = new ColoredText(Game.token, Game.player1color).toString();
                    continue;
                }

                if (boardMatrix[i][j].equals("1")) {
                    stylizedBoard[i][j] = new ColoredText(Game.token, Game.player2color).toString();
                    continue;
                }

                stylizedBoard[i][j] = " ";
            }
        }

        return new Tabulate(stylizedBoard, true).toString();
    }
}
