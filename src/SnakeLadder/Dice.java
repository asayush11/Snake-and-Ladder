package src.SnakeLadder;

public class Dice {

    private final int diceCount;
    private final int min;
    private final int max;

    public Dice(int diceCount, int min, int max) {
        this.diceCount = diceCount;
        this.min = min;
        this.max = max;
    }

    public int rollDice(){

        int totalSum=0;
        int diceUsed=0;

        while(diceUsed<diceCount){

            totalSum += (int)(Math.random()*(max-min+1))+1;
            diceUsed++;
        }

        return totalSum;
    }
}
