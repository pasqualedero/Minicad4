package command.specificcommand;

import command.Command;
import id.IdGenerator;
import interpreter.ListID;
import interpreter.ObjID;
import shapes.model.CompositeObject;
import shapes.model.GraphicObject;
import shapes.view.GraphicObjectPanel;

import javax.swing.*;
import java.util.LinkedList;

public class GroupCmd implements Command {

    private LinkedList<ObjID> lista;
    private GraphicObjectPanel gpanel;
    private GraphicObject composite;

    public GroupCmd(ListID lista, GraphicObjectPanel gpanel) {
        this.lista = lista.getListaID();
        this.gpanel = gpanel;
    }

    @Override
    public boolean doIt() {
        LinkedList<Integer> listaInteger = new LinkedList<>();
        String res="";
        for (ObjID id : lista){
            IdGenerator.ISTANCE.get(id.getId()); // vedo se lancia eccezione
            listaInteger.add(id.getId());
            res += IdGenerator.ISTANCE.get(id.getId()).toString()+"\n";
        }

        this.composite = new CompositeObject(listaInteger);
        int compositeId = IdGenerator.ISTANCE.add(composite);
        System.out.println("ID: "+compositeId);

        JOptionPane.showMessageDialog(gpanel,"ID gruppo: "+compositeId+"\n------------------\nComponenti:\n"+res,
                "Creazione Gruppo", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/info.png")));


        return false;
    }

    @Override
    public GraphicObject getGraphicObject() {
        return composite;
    }
}
