package service;

import java.util.Random;

public class DiceService {
    public static int roll() {
        return new Random().nextInt(6) + 1;
    }

    public static int rollEven() {
        return (new Random().nextInt(3) + 1) * 2;
    }
}
