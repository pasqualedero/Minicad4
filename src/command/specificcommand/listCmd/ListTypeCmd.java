package command.specificcommand.listCmd;

import command.Command;
import command.DeclarativeCommand;
import id.IdGenerator;
import interpreter.Simboli;
import interpreter.Type;
import shapes.model.*;

import javax.swing.*;
import java.util.Set;

public class ListTypeCmd implements Command , DeclarativeCommand {
    private Simboli tipo;

    public ListTypeCmd(Type tipo) {
        this.tipo = tipo.getSimbolo();
    }


    @Override
    public boolean doIt() {
        String res ="";
        Set<Integer> listaID = IdGenerator.ISTANCE.getKeySet();
        Class<? extends GraphicObject> classe = null;

        switch (tipo){
            case IMAGE -> classe = ImageObject.class;
            case RECTANGLE -> classe = RectangleObject.class;
            case CIRCLE -> classe = CircleObject.class;
        }

        for (Integer i : listaID){
            GraphicObject go = IdGenerator.ISTANCE.get(i);
            if (classe.isInstance(go))
                res += go+"\n";
        }
        System.out.println(res);
        JOptionPane.showMessageDialog(null,res,
                "Operazione avvenuta con successo!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));

        return false;
    }

    @Override
    public GraphicObject getGraphicObject() {
        return null;
    }
}
