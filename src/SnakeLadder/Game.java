package src.SnakeLadder;

import java.util.Deque;
import java.util.LinkedList;

public class Game {

    private final Board board;
    private final Dice dice;
    private final Deque<Player> playersList = new LinkedList<>();
    private Player winner;

    public Game(){
        this.board = new Board(100, 5, 4);
        this.dice = new Dice(1, 1, 6);
        addPlayers();
    }


    private void addPlayers() {
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);
        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame(){

        while(winner == null) {

            //check whose turn now
            Player playerTurn = findPlayerTurn();
            System.out.println("player turn is:" + playerTurn.getId() + " current position is: " + playerTurn.getCurrentPosition());

            //roll the dice
            int diceNumbers = dice.rollDice();

            //get the new position
            int playerNewPosition = playerTurn.getCurrentPosition() + diceNumbers;
            while(true){
                int playerPositionAfterJump = jumpCheck(playerNewPosition);
                if(playerPositionAfterJump==playerNewPosition) break;
                playerNewPosition = playerPositionAfterJump;
            }
            playerTurn.setCurrentPosition(playerNewPosition);

            System.out.println("player turn is:" + playerTurn.getId() + " new Position is: " + playerNewPosition);
            //check for winning condition
            if(playerNewPosition >= board.getCells().size()-1){
                winner = playerTurn;
            }

        }

        System.out.println("WINNER IS:" + winner.getId());
    }


    private Player findPlayerTurn() {
        Player playerTurns = playersList.removeFirst();
        playersList.addLast(playerTurns);
        return playerTurns;
    }

    private int jumpCheck (int playerNewPosition) {
        if(playerNewPosition > board.getCells().size()-1 ){
            return playerNewPosition;
        }

        Cell cell = board.getCell(playerNewPosition);
        if(cell.getJump() != null && cell.getJump().getStart() == playerNewPosition) {
            String jumpBy = (cell.getJump().getStart() < cell.getJump().getEnd())? "ladder" : "snake";
            System.out.println("jump done by: " + jumpBy);
            return cell.getJump().getEnd();
        }
        return playerNewPosition;
    }
}
