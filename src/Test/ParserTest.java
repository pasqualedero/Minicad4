package Test;

import exception.InvalidID;
import id.IdGenerator;
import interpreter.Parser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shapes.view.GraphicObjectPanel;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {


    @ParameterizedTest
    @ValueSource(strings = {"new circle (80) (90.8,400.23)","scale 1 100", "ls 1","perimeter all","new rectangle 80 80 (80,80)","grp 1 2"})
    public void testParseValidCommand(String combinazione) {
        StringReader sr = new StringReader(combinazione);
        Parser parser = new Parser(sr);

        assertTrue(parser.getCombinazione().interpreta(new GraphicObjectPanel(), combinazione) != null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"scale 1 100","mv 3 (500,500)"}) //darà errore, visto che sto compiendo operazioni su ID inesistenti
    public void testParseInvalidCommand(String combinazione) {
        IdGenerator.ISTANCE.clear();
        StringReader sr = new StringReader(combinazione);
        Parser parser = new Parser(sr);

        assertThrows(InvalidID.class,()->parser.getCombinazione().interpreta(new GraphicObjectPanel(), combinazione));
    }

}

