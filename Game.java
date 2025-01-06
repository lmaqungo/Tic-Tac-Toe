import java.util.Arrays;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private Board board;
    private Player[] players;
    private Player currentPlayer;
    private int round;

    public Game(){
        board = new Board(3);
        players = new Player[2];
        round = 1;
    }

    public void start(){
        System.out.print("Player 1. Enter your name: ");
        String playerOneName = scanner.next();
        Player playerOne = new Player(playerOneName, 'X');
        players[0] = playerOne;
        System.out.print("Player 2. Enter your name: ");
        String playerTwoName = scanner.next();
        Player playerTwo = new Player(playerTwoName, 'O');
        players[1] = playerTwo;
        currentPlayer = playerOne;
    }

    public void loop(){
        while (true){
            System.out.printf("%s: Enter a row and column in a comma-separated list: ", currentPlayer.getName());
            try{
                String[] coordinates = scanner.next().trim().split(",");
                int rowIndex = Integer.parseInt(coordinates[0]);
                int columnIndex = Integer.parseInt(coordinates[1]);
                if(board.placeMarker(rowIndex, columnIndex, currentPlayer.getMarker())){
                    board.display();
                }else{
                    System.out.println("Occupied cell!");
                }
            } catch (NumberFormatException e){
                System.out.println("Use a comma separator!");
                continue;
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Input must be between 1 and 3!");
                continue;
            }
            if (checkWin()){
                if(restart()){
                    continue;
                }else{
                    break;
                }
            }else if(isDraw()){
                if(restart()){
                    continue;
                }else{
                    break;
                }
            } else {
                switchPlayer();
            }
            // check win(stop inputs, print winner), check draw(board filled up, no wins, stop inputs), otherwise switch player
        }
    }

    private boolean checkWin(){
        char[] winningLane = new char[]{currentPlayer.getMarker(), currentPlayer.getMarker(), currentPlayer.getMarker()};
            for(LegalWins lane: LegalWins.values()) {
                char[] checkLane = new char[]{board.getCell(lane.getCell1()[0], lane.getCell1()[1]), board.getCell(lane.getCell2()[0], lane.getCell2()[1]), board.getCell(lane.getCell3()[0], lane.getCell3()[1])};
                if (Arrays.equals(checkLane, winningLane)) {
                    System.out.printf("%s wins!%n", currentPlayer.getName());
                    currentPlayer.incrementScore();
                    return true;
                }
                checkLane = new char[0];
            }

        return false;
    }

    private boolean restart(){
        displayScore();
        while(true){
            System.out.print("Do you wish to continue?: ");
            String choice = scanner.next().trim().toLowerCase();
            if (choice.equals("y")){
                board.emptyBoard();
                incrementRound();
                return true;
            } else if (choice.equals("n")) {
                System.out.println("Exiting Game");
                setRound(1);
                return false;
            }else{
                System.out.println("Only \"y\" or \"no\" input accepted");
            }
        }


    }

    private void displayScore(){
        System.out.printf("Round %d Scores:%n", getRound());
        for(Player player: players){
            System.out.printf("%s: %d points%n", player.getName(), player.getScore());
        }
    }


    private boolean isDraw(){
        if (board.fullBoard()){
            System.out.printf("Draw!%n");
            return true;
        } else{
            return false;
        }
    }

    private int getRound(){return round;}

    private void setRound(int i){
        round = i;
    }

    private void incrementRound(){
        round+=1;
    }



    private void switchPlayer(){
        if (currentPlayer == players[0]){
            currentPlayer = players[1];
        }else{
            currentPlayer = players[0];
        }
    }

    public enum LegalWins{
        ROW_1 (new int[]{1, 1}, new int[]{1,2}, new int[]{1,3}),
        ROW_2 (new int[]{2, 1}, new int[]{2,2}, new int[]{2,3}),
        ROW_3 (new int[]{3, 1}, new int[]{3,2}, new int[]{3,3}),
        COL_1 (new int[]{1, 1}, new int[]{2,1}, new int[]{3,1}),
        COL_2 (new int[]{1, 2}, new int[]{2,2}, new int[]{3,2}),
        COL_3 (new int[]{1, 3}, new int[]{2,3}, new int[]{3,3}),
        DIAG_1 (new int[]{1, 1}, new int[]{2,2}, new int[]{3,3}),
        DIAG_2 (new int[]{1, 3}, new int[]{2,2}, new int[]{3,1});

        private int[] cell1;
        private int[] cell2;
        private int[] cell3;

        private LegalWins(int[] cell1, int[] cell2, int[] cell3){
            this.cell1 = cell1;
            this.cell2 = cell2;
            this.cell3 = cell3;
        }

        public int[] getCell1(){
            return cell1;
        }
        public int[] getCell2(){
            return cell2;
        }
        public int[] getCell3(){
            return cell3;
        }

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
        game.loop();
    }

}
