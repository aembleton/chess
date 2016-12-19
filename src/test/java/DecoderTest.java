import com.clicktravel.chess.exception.DecodingExcpetion;
import com.clicktravel.chess.service.Decoder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by arthur on 19/12/16.
 */
public class DecoderTest {

    @Test
    public void happyPath() throws Exception{
        String input = "r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1";
        List<String> output = Decoder.decode(input);

        assertEquals(8, output.size());
        assertEquals("r.bk...r", output.get(0));
        assertEquals("p..pBpNp", output.get(1));
        assertEquals("n....n..", output.get(2));
        assertEquals(".p.NP..P", output.get(3));
        assertEquals("......P.", output.get(4));
        assertEquals("...P....", output.get(5));
        assertEquals("P.P.K...", output.get(6));
        assertEquals("q.....b.", output.get(7));
    }

    @Test
    public void wrongNumberOfRows() {
        String input = "r1bk3r/p2pBpNp";

        try {
            Decoder.decode(input);
            fail("A DecodingException should have been thrown");
        } catch(DecodingExcpetion e) {
            assertEquals(e.getMessage(), "Wrong number of rows");
        }
    }

    @Test
    public void wrongNumberOfSquaresOnRow() {
        String input = "r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b2";

        try {
            Decoder.decode(input);
            fail("A DecodingException should have been thrown");
        } catch(DecodingExcpetion e) {
            assertEquals(e.getMessage(), "Wrong number of squares on row");
        }
    }

    @Test
    public void unexpectedCharacter() {
        String input = "3w4/7p/7p/7p/8/8/8/8";

        try {
            Decoder.decode(input);
            fail("A DecodingException should have been thrown");
        } catch(DecodingExcpetion e) {
            assertEquals(e.getMessage(), "Unexpected character");
        }
    }
}
