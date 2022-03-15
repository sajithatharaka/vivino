package functions;

import java.util.Random;

public class RandomData {
    public static int getNumberBetween(int max){
        Random random = new Random();
        int value = random.nextInt(max);
        Logs.log("Random Number :: "+value);
        return value;
    }
}
