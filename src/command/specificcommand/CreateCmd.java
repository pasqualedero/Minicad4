package command.specificcommand;

import command.Command;
import exception.UnresolvedPath;
import id.IdGenerator;
import interpreter.Pos;
import interpreter.Simboli;
import interpreter.typeConstr.TypeConstr;
import interpreter.typeConstr.TypeConstrImg;
import shapes.model.CircleObject;
import shapes.model.GraphicObject;
import shapes.model.ImageObject;
import shapes.model.RectangleObject;
import shapes.view.GraphicObjectPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CreateCmd implements Command {
    private TypeConstr tc;
    private Pos pos;
    private GraphicObject go;
    private GraphicObjectPanel panel;
    private int id;
    private final static HashMap<Simboli, GraphicObject> LISTA_PROTOTIPI = new HashMap<>();

    public CreateCmd(TypeConstr tc, Pos pos, GraphicObjectPanel panel) {
        LISTA_PROTOTIPI.put(Simboli.CIRCLE,new CircleObject(new Point(200, 100), 10));
        LISTA_PROTOTIPI.put(Simboli.RECTANGLE, new RectangleObject(new Point(180, 80), 20, 50));

        this.panel=panel;
        this.tc = tc;
        this.pos = pos;

        if (tc.getTipo().getSimbolo()==Simboli.IMAGE){
            TypeConstrImg tci = (TypeConstrImg) tc;
            ImageIcon icon = new ImageIcon(tci.getPath());
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            } else {
                throw new UnresolvedPath("Errore nel caricamento dell'immagine: path invalido. " + icon.getDescription());
            }
            go = new ImageObject(icon, new Point(240, 187));

        } else {

            GraphicObject prototype = LISTA_PROTOTIPI.get(tc.getTipo().getSimbolo());
            this.go = prototype.clone();
        }

        this.id = IdGenerator.ISTANCE.add(go);
        System.out.println("ID: "+id);
    }

    @Override
    public boolean doIt() {

        go.moveTo(pos.getX().getPos(),pos.getY().getPos());
        go.setDimension(tc);
        panel.add(go);

        JOptionPane.showMessageDialog(panel, "Creazione di:\n"+go.toString()+".\n"+"ID: "+id,
                "Creazione avvenuta con successo!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));

        return false;
    }


    @Override
    public GraphicObject getGraphicObject() {
        return go;
    }


}
