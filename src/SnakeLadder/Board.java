package src.SnakeLadder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    private final List<Cell> cells;

    Board(int boardSize, int numberOfSnakes, int numberOfLadders) {
        cells = new ArrayList<>();
        initializeCells(boardSize);
        addSnakesLadders(cells, numberOfSnakes, numberOfLadders);
    }

    private void initializeCells(int boardSize) {
        for(int i=0;i<boardSize;i++) {
                Cell cellObj = new Cell();
                cells.add(cellObj);
            }
    }

    private void addSnakesLadders(List<Cell> cells, int numberOfSnakes, int numberOfLadders){

        while(numberOfSnakes > 0) {
           int snakeHead = (int)(Math.random()* cells.size());
           int snakeTail = (int)(Math.random()* cells.size());
           if(snakeTail >= snakeHead) {
               continue;
           }

           Jump snakeObj = new Jump(snakeHead, snakeTail);
           Cell cell = getCell(snakeHead);
           if(cell.getJump() != null) {
               continue;
           }
           cell.setJump(snakeObj);
           numberOfSnakes--;
        }

        while(numberOfLadders > 0) {
            int ladderStart = (int)(Math.random()* cells.size());
            int ladderEnd =   (int)(Math.random()* cells.size());
            if(ladderStart >= ladderEnd) {
                continue;
            }

            Jump ladderObj = new Jump(ladderStart, ladderEnd);
            Cell cell = getCell(ladderStart);
            if(cell.getJump() != null) {
                continue;
            }
            cell.setJump(ladderObj);
            numberOfLadders--;
        }

    }

    Cell getCell(int playerPosition) {
        return cells.get(playerPosition);
    }

    public List<Cell> getCells() {
        return cells;
    }
}
