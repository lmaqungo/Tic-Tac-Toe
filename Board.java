
public class Board {
    private char[][] grid;
    private int size;

    public Board(int size){

        this.size = size;
        grid = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    public void display(){
        for(int i=0; i < size; i++){
            for (int j=0; j < size; j++){
                System.out.printf("%c ", grid[i][j]);
            }
            System.out.println();
        }
    }

    public boolean placeMarker(int row, int col, char marker){
        if (isCellEmpty(row, col)){
            grid[row-1][col-1] = marker;
            return true;
        }else{
            return false;
        }
    }

    public boolean fullBoard(){
        int filled = 0;
        for(int i=0; i < size; i++){
            for (int j=0; j < size; j++){
                if(grid[i][j] != ' '){
                    filled+= 1;
                }
                }
            }
        return filled == 9;
    }


    public boolean isCellEmpty(int row, int col){
        if (grid[row-1][col-1] == ' '){
            return true;
        } else{
            return false;
        }
    }

    public void emptyBoard(){
        grid = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    public char getCell(int row, int col){
        return grid[row-1][col -1];
    }
}
