package service;

import java.util.Random;
import java.util.logging.Logger;

public class DiceService {
    static Logger logger = Logger.getLogger(DiceService.class.getName());

    public  int roll() {
        logger.info("Rolling a fair dice");
        return new Random().nextInt(6) + 1;
    }

    public  int rollEven() {
        logger.info("Rolling an even dice");
        return (new Random().nextInt(3) + 1) * 2;
    }
}
