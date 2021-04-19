package service;


import model.Ladder;
import model.Player;
import model.Snake;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BoardServiceTest {

    @Mock GameInputService gameInputService;

    @Test
    public void shouldCreateBoardWithSpecifiedPlayers()
    {
        when(gameInputService.getLadders()).thenReturn(List.of(new Ladder(2,5)));
        when(gameInputService.getPlayers()).thenReturn(List.of(new Player("ujjavala")));
        when(gameInputService.getSnakes()).thenReturn(List.of(new Snake(6,87)));
        BoardService service = new BoardService(gameInputService);
        service.createBoard();
        Assert.assertEquals(1,service.getPlayers().size());
    }

    @Test
    public void shouldGetPlayerName()
    {
        when(gameInputService.getLadders()).thenReturn(List.of(new Ladder(2,5)));
        when(gameInputService.getPlayers()).thenReturn(List.of(new Player("ujjavala")));
        when(gameInputService.getSnakes()).thenReturn(List.of(new Snake(6,87)));
        BoardService service = new BoardService(gameInputService);
        service.createBoard();
        Assert.assertEquals("ujjavala",service.getPlayers().peek().getName());
    }
}
