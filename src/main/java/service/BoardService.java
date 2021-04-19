package service;

import model.Board;
import model.Ladder;
import model.Player;
import model.Snake;

import java.util.*;
import java.util.logging.Logger;

import static util.Constants.DEFAULT_BOARD_SIZE;

public class BoardService {
    static Logger logger = Logger.getLogger(BoardService.class.getName());

    private final List<Player> gamePlayers;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;

    public Queue<Player> getPlayers() {
        return players;
    }

    private Queue<Player> players;


    public BoardService(GameInputService gameInputService) {
        logger.info("initializing board service");

        gamePlayers = gameInputService.getPlayers();
        snakes = gameInputService.getSnakes();
        ladders = gameInputService.getLadders();
    }

    public Board createBoard() {
        logger.info("creating board");
        this.players = new LinkedList<>();
        Board board = null;
        Map<String, Integer> playerPieces = new HashMap<>();
        for (Player player : gamePlayers) {
            this.players.add(player);
            playerPieces.put(player.getName(), 0);
            board = new Board(DEFAULT_BOARD_SIZE, snakes, ladders, playerPieces);
        }
        logger.info("board successfully created");
        return board;
    }
}
