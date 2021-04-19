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

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    @Mock BoardService boardService;
    @Test
    public void shouldExecuteGame(){


        Map<String,Integer> initialPlayer = new HashMap<>();
        Queue<Player> player = new LinkedList<>();
        player.add(new Player("ujjavala"));
        initialPlayer.put("ujjavala",0);

        when(boardService.createBoard()).thenReturn(new Board(100, List.of(new Snake(3,5)),List.of(new Ladder(8,67)),initialPlayer));
        when(boardService.getPlayers()).thenReturn(player);

        GameService service = new GameService(boardService);
        service.startGame();
        Assert.assertEquals("ujjavala",service.getWinner());
    }
}
