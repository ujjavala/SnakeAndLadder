package service;

import model.Board;
import model.Ladder;
import model.Player;
import model.Snake;
import util.Constants;

import java.util.Queue;
import java.util.logging.Logger;

import static service.DiceService.roll;
import static service.DiceService.rollEven;

public class GameService {
    private final Board board;
    private final int initialNumberOfPlayers;
    private final Queue<Player> players;
    private String winner;
    static Logger logger = Logger.getLogger(GameService.class.getName());

    public GameService(BoardService boardService) {
        logger.info("initializing game service");
        board = boardService.createBoard();
        players = boardService.getPlayers();
        initialNumberOfPlayers = players.size();
    }

    private int getNewPosition(int newPosition) {
        logger.info("getting new position");

        int previousPosition;
        do {
            previousPosition = newPosition;
            for (Snake snake : board.getSnakes())
                if (snake.getStart() == newPosition) newPosition = snake.getEnd();

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
        newPosition = newPosition > boardSize ? oldPosition : getNewPosition(newPosition);
        board.getPlayerPieces().put(player.getName(), newPosition);
    }

    private int getTotalValueAfterDiceRolls() {
        logger.info("getting  totla value");
        return !Constants.DICE_TYPE.equals("FAIR") ? rollEven() : roll();
    }

    private boolean hasPlayerWon(Player player) {
        int playerPosition = board.getPlayerPieces().get(player.getName());
        int winningPosition = board.getSize();
        return playerPosition == winningPosition;
    }

    private boolean isGameCompleted() {
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < initialNumberOfPlayers;
    }

    public void startGame() {
        logger.info("starting game");

        while (!isGameCompleted()) {
            int totalDiceValue = getTotalValueAfterDiceRolls();
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