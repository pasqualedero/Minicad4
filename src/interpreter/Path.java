package interpreter;

import command.Command;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class Path implements CombinazioneIntermedia{
    private String path;

    public Path(String path) {
        try {
            Paths.get(path);
            this.path=path;
        }catch (InvalidPathException | NullPointerException ex){
            System.out.println("Path invalido");
            ex.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    @Override
    public CombinazioneIntermedia interpreta(Command contesto, String input) {
        //contesto.setTesto(" path:"+path);
        return this;
    }
}
