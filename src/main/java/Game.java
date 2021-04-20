import service.BoardService;
import service.DiceService;
import service.GameInputService;
import service.GameService;

public class Game {
    public static void main(String[] args) {
        GameInputService gameInputService = new GameInputService();
        BoardService boardService = new BoardService(gameInputService);
        GameService gameService = new GameService(boardService,new DiceService());
        gameService.startGame();
        System.out.println("Winner is " + gameService.getWinner());
    }
}
