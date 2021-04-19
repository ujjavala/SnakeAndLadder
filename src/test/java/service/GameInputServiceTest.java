package service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

@RunWith(MockitoJUnitRunner.class)
public class GameInputServiceTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test()
    public void shouldGetInputCorrectly()
    {
        String input = "add 5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        exception.expect(InputMismatchException.class);
        GameInputService service = new GameInputService();
    }
}
