package interpreter.move;

import interpreter.CombinazioneTerminale;
import interpreter.ObjID;
import interpreter.Pos;

public abstract class Move implements CombinazioneTerminale {
    ObjID id;
    Pos pos;

    public ObjID getId() {
        return id;
    }

    public Pos getPos() {
        return pos;
    }
}
