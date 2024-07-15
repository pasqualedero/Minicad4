package interpreter;

import command.Command;

public interface CombinazioneIntermedia {
    public CombinazioneIntermedia interpreta(Command comando, String input);
}
