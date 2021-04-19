package model;

import java.util.List;
import java.util.Map;

public class Board {

    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Map<String, Integer> playerPieces;

    public Board(int size, List<Snake> snakes, List<Ladder> ladders, Map<String, Integer> playerPieces) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playerPieces = playerPieces;
    }

    public int getSize() {
        return size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }
    public List<Ladder> getLadders() {
        return ladders;
    }

    public Map<String, Integer> getPlayerPieces() {
        return playerPieces;
    }

}
