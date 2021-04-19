package service;

import model.Ladder;
import model.Player;
import model.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameInputService {

    private final List<Player> players;
    private final List<Ladder> ladders = new ArrayList<>();
    private final List<Snake> snakes = new ArrayList<>();
    static Logger logger = Logger.getLogger(GameInputService.class.getName());

    public GameInputService() {
        logger.info("initializing gameinput service constructor");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of players:");
        int noOfPlayers = scanner.nextInt();
        players = IntStream.range(0, noOfPlayers).mapToObj(element -> new Player(scanner.next())).collect(Collectors.toList());

        inputForSnakes(scanner);
        inputForLadders(scanner);
        logger.info("successfully read input from user");

    }

    private void inputForLadders(Scanner scanner) {
        System.out.println("Number of ladders:");
        int noOfLadders = scanner.nextInt();

        System.out.println("Enter start and end for each ladder within 1 to 100");
        IntStream.range(0, noOfLadders).map(index -> scanner.nextInt()).forEach(startPosition -> {
            int endPosition = scanner.nextInt();
            if (validateInput(startPosition, endPosition))
                ladders.add(new Ladder(startPosition, endPosition));
            else try {
                throw new Exception("Invalid Input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        logger.info("successfully read input for ladder");

    }

    private void inputForSnakes(Scanner scanner) {
        System.out.println("Number of snakes:");
        int noOfSnakes = scanner.nextInt();
        System.out.println("Enter start and end for each snake within 1 to 100");

        IntStream.range(0, noOfSnakes).map(index -> scanner.nextInt()).forEach(startPosition -> {
            int endPosition = scanner.nextInt();
            if (validateInput(startPosition, endPosition))
                snakes.add(new Snake(startPosition, endPosition));
            else try {
                throw new Exception("Invalid Input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        logger.info("successfully read input for snakes");

    }

    public boolean validateInput(int start, int end) {
        logger.info("validating input");

        return start < end && start >= 0 && start < 100 && end <= 100;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
