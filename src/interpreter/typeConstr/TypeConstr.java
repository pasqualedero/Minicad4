package interpreter.typeConstr;

import interpreter.CombinazioneIntermedia;
import interpreter.Type;

public abstract class TypeConstr implements CombinazioneIntermedia {
    public abstract Type getTipo();

}

/**
 * POTEVO USARE DEI DESIGN PATTERN?
 * - Factory Method
 *      Non lo uso perchè la classe astratta non riuscirebbe ad eseguire alcun metodo delle classi concrete, risultando
 *      così vana l'idea di spostare il "codice comune" all'interno di essa.
 * - Strategy
 *      Non lo uso perchè avrei bisogno di due strategie, una per il parser ed una per le interpretazioni delle varianti
 *      di TypeConstr; il codice risulterebbe molto esteso.
 */
