package service;

import model.Ladder;
import model.Player;
import model.Snake;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameInputService {

    private final List<Player> players;
    private final List<Ladder> ladders;
    private final List<Snake> snakes;

    public GameInputService()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Number of players:");
        int noOfPlayers = scanner.nextInt();
        players = IntStream.range(0, noOfPlayers).mapToObj(element -> new Player(scanner.next())).collect(Collectors.toList());

        System.out.println("Number of snakes:");
        int noOfSnakes = scanner.nextInt();
        System.out.println("Enter start and end for each snake");
        snakes = IntStream.range(0, noOfSnakes).mapToObj(element -> new Snake(scanner.nextInt(), scanner.nextInt())).collect(Collectors.toList());

        System.out.println("Number of ladders:");
        int noOfLadders = scanner.nextInt();
        System.out.println("Enter start and end for each ladder");
        ladders = IntStream.range(0, noOfLadders).mapToObj(element -> new Ladder(scanner.nextInt(), scanner.nextInt())).collect(Collectors.toList());

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
