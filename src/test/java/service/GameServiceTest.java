package service;

import model.Board;
import model.Ladder;
import model.Player;
import model.Snake;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;
import static util.Constants.GREEN_SNAKE_END;
import static util.Constants.GREEN_SNAKE_START;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    @Mock BoardService boardService;

    @Mock
    DiceService diceService;

    @Test
    public void shouldExecuteGame(){

        Map<String,Integer> initialPlayer = new HashMap<>();
        Queue<Player> player = new LinkedList<>();
        player.add(new Player("ujjavala"));
        initialPlayer.put("ujjavala",0);

        when(boardService.createBoard()).thenReturn(new Board(100, List.of(new Snake(3,5)),List.of(new Ladder(8,67)),initialPlayer));
        when(boardService.getPlayers()).thenReturn(player);

        when(diceService.roll()).thenReturn(2);
        GameService service = new GameService(boardService,diceService);
        service.startGame();
        Assert.assertEquals("ujjavala",service.getWinner());
    }

    @Test
    public void executeForGreenSnake(){

        Map<String,Integer> initialPlayer = new HashMap<>();
        Queue<Player> player = new LinkedList<>();
        player.add(new Player("ujjavala"));
        initialPlayer.put("ujjavala",0);

        when(boardService.createBoard()).thenReturn(new Board(100, List.of(new Snake(GREEN_SNAKE_START, GREEN_SNAKE_END)),List.of(new Ladder(8,67)),initialPlayer));
        when(boardService.getPlayers()).thenReturn(player);
        when(diceService.roll()).thenReturn(10);

        GameService service = new GameService(boardService,diceService);
        service.startGame();
        Assert.assertEquals("ujjavala",service.getWinner());
    }
}
