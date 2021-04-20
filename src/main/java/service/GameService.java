package service;

import model.Board;
import model.Ladder;
import model.Player;
import model.Snake;
import util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

//import static service.DiceService.roll;
//import static service.DiceService.rollEven;

public class GameService {
    private final Board board;
    private final int initialNumberOfPlayers;
    private final Queue<Player> players;
    private String winner;
    private List<Player> greenSnakeBittenPlayersList = new ArrayList<>();
    private DiceService diceService;

    public void addBittenPlayers(Player player) {
        greenSnakeBittenPlayersList.add(player);
    }

    static Logger logger = Logger.getLogger(GameService.class.getName());


    public GameService(BoardService boardService,DiceService diceService) {
        logger.info("initializing game service");
        board = boardService.createBoard();
        players = boardService.getPlayers();
        initialNumberOfPlayers = players.size();
        this.diceService = diceService;
    }

    private int getNewPosition(int newPosition, Player player) {
        logger.info("getting new position");
        int previousPosition;
        do {
            previousPosition = newPosition;
            for (Snake snake : board.getSnakes()) {
                if (snake.getStart() == newPosition) {
                    if (snake.getStart() == Constants.GREEN_SNAKE_START && greenSnakeBittenPlayersList.contains(player))
                        newPosition = snake.getStart();
                   else {
                        if(snake.getStart() == Constants.GREEN_SNAKE_START && !greenSnakeBittenPlayersList.contains(player))
                            addBittenPlayers(player);
                        newPosition = snake.getEnd();
                    }
                }
            }
            for (Ladder ladder : board.getLadders())
                if (ladder.getStart() == newPosition) newPosition = ladder.getEnd();
        } while (newPosition != previousPosition);
        return newPosition;
    }

    private void movePlayer(Player player, int positions) {
        logger.info("moving player");

        int oldPosition = board.getPlayerPieces().get(player.getName());
        int newPosition = oldPosition + positions;
        int boardSize = board.getSize();
        newPosition = newPosition > boardSize ? oldPosition : getNewPosition(newPosition, player);
        board.getPlayerPieces().put(player.getName(), newPosition);
    }

    private int getTotalValue() {
        logger.info("getting  totla value");
        return !Constants.DICE_TYPE.equals("FAIR") ? diceService.rollEven() : diceService.roll();
    }

    private boolean hasPlayerWon(Player player) {
        int playerPosition = board.getPlayerPieces().get(player.getName());
        int winningPosition = board.getSize();
        return playerPosition == winningPosition;
    }

    private boolean isGameOver() {
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < initialNumberOfPlayers;
    }

    public void startGame() {
        logger.info("starting game");

        while (!isGameOver()) {
            int totalDiceValue = getTotalValue();
            Player currentPlayer = players.poll();
            assert currentPlayer != null;
            movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                winner = currentPlayer.getName();
                board.getPlayerPieces().remove(currentPlayer.getName());
            } else {
                players.add(currentPlayer);
            }
        }
    }

    public String getWinner() {
        logger.info("returning winner");
        return winner;
    }

}