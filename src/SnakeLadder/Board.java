package src.SnakeLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Board {

    Cell[] cells;

    Board(int boardSize, int numberOfSnakes, int numberOfLadders) {

        initializeCells(boardSize);
        addSnakesLadders(cells, numberOfSnakes, numberOfLadders);
    }

    private void initializeCells(int boardSize) {

        cells = new Cell[boardSize];

        for(int i=0;i<boardSize;i++) {
                Cell cellObj = new Cell();
                cells[i] = cellObj;
            }
        }

    private void addSnakesLadders(Cell[] cells, int numberOfSnakes, int numberOfLadders){

        while(numberOfSnakes > 0) {
           int snakeHead = (int)(Math.random()* cells.length);
           int snakeTail = (int)(Math.random()* cells.length);
           if(snakeTail >= snakeHead) {
               continue;
           }

           Jump snakeObj = new Jump();
           snakeObj.start = snakeHead;
           snakeObj.end = snakeTail;

           Cell cell = getCell(snakeHead);
           cell.jump = snakeObj;

            numberOfSnakes--;
        }

        while(numberOfLadders > 0) {
            int ladderStart = (int)(Math.random()* cells.length);
            int ladderEnd =   (int)(Math.random()* cells.length);
            if(ladderStart >= ladderEnd) {
                continue;
            }

            Jump ladderObj = new Jump();
            ladderObj.start = ladderStart;
            ladderObj.end = ladderEnd;

            Cell cell = getCell(ladderStart);
            cell.jump = ladderObj;

            numberOfLadders--;
        }

    }

    Cell getCell(int playerPosition) {
        return cells[playerPosition];
    }
}
