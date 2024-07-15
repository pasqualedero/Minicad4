package interpreter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class AnalizzatoreLessicale {
    private StreamTokenizer input; // configura l'analizzatore
    private Simboli simbolo;

    public AnalizzatoreLessicale(Reader in) {

        input = new StreamTokenizer(in);
        input.resetSyntax();
        input.eolIsSignificant(false);
        input.wordChars('a', 'z');
        input.wordChars('A', 'Z');
        input.wordChars('0', '9');
        input.wordChars('.', '.');
        input.wordChars('/','/');
        input.whitespaceChars('\u0000', ' ');
        input.ordinaryChar('(');
        input.ordinaryChar(')');
    }

    public String getString() {
        return input.sval;
    } // valore del token corrente

    public Simboli getSimbolo(){
        return simbolo;
    }
    public Simboli prossimoSimbolo(){
        try{
            switch (input.nextToken()) {
            case StreamTokenizer.TT_EOF:
                simbolo = Simboli.EOE;
                break;
            case StreamTokenizer.TT_WORD:
                // verifica prima se la parola e' riservata
                if (input.sval.equalsIgnoreCase("ls"))
                    simbolo = Simboli.LS;
                else if (input.sval.equalsIgnoreCase("grp"))
                    simbolo = Simboli.GRP;
                else if (input.sval.equalsIgnoreCase("ungrp"))
                    simbolo = Simboli.UNGRP;
                else if (input.sval.equalsIgnoreCase("scale"))
                    simbolo = Simboli.SCALE;
                else if (input.sval.equalsIgnoreCase("del"))
                    simbolo = Simboli.DEL;
                else if (input.sval.equalsIgnoreCase("img"))
                    simbolo = Simboli.IMAGE;
                else if (input.sval.equalsIgnoreCase("circle"))
                    simbolo = Simboli.CIRCLE;
                else if (input.sval.equalsIgnoreCase("rectangle"))
                    simbolo = Simboli.RECTANGLE;
                else if (input.sval.equalsIgnoreCase("new"))
                    simbolo = Simboli.NEW;
                else if (input.sval.equalsIgnoreCase("mvoff"))
                    simbolo = Simboli.MVOFF;
                else if (input.sval.equalsIgnoreCase("mv"))
                    simbolo = Simboli.MV;
                else if (input.sval.equalsIgnoreCase("perimeter"))
                    simbolo = Simboli.PERIMETER;
                else if (input.sval.equalsIgnoreCase("all"))
                    simbolo = Simboli.ALL;
                else if (input.sval.equalsIgnoreCase("area"))
                    simbolo = Simboli.AREA;
                else
                    // parola normale - nome o numero
                    simbolo = Simboli.NUMERO_O_STRINGA;
                break;
            case ',':
                simbolo = Simboli.VIRGOLA;
                break;
            case '(':
                simbolo = Simboli.TONDA_APERTA;
                break;
            case ')':
                simbolo = Simboli.TONDA_CHIUSA;
                break;
            default:
                simbolo = Simboli.CHAR_INVALIDO;
        }
    } catch (IOException e) {
        simbolo = Simboli.EOE;
    }
		return simbolo;    }

}
