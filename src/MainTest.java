import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void render_game() {
        ArrayList<Character> a = new ArrayList<Character>(Arrays.asList('k', 't', 'n'));
        assertEquals("k_tt_n",Main.render_game("kitten",a));
    }
    @Test
    void process_guess(){
        assertFalse(Main.process_guess('u',"java"));
        assertTrue(Main.process_guess('g',"game"));
    }
    @Test
    void high_score() throws IOException {
        assertFalse(Main.high_score(Path.of("records.txt"),5));
        assertTrue(Main.high_score(Path.of("records.txt"),0));
        assertFalse(Main.high_score(Path.of("records.txt"),7));
        assertTrue(Main.high_score(Path.of("emptyTest.txt"),7));
    }

}