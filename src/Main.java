import command.Command;
import command.HistoryCommandHandler;
import interpreter.Parser;
import shapes.model.CircleObject;
import shapes.model.ImageObject;
import shapes.model.RectangleObject;
import shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {

        JFrame f = new JFrame();

        JToolBar toolbar = new JToolBar();

        JButton undoButt = new JButton("Undo");

        final HistoryCommandHandler handler = new HistoryCommandHandler();

        undoButt.addActionListener(evt -> handler.undo());


        toolbar.add(undoButt);

        final GraphicObjectPanel gpanel = new GraphicObjectPanel();

        gpanel.setPreferredSize(new Dimension(800, 800));

        // Per far comparire le scrollbar
        gpanel.setMaximumSize(new Dimension(100, 100));
        gpanel.setMinimumSize(new Dimension(100, 100));

        GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());


        JTextField textField = new JTextField(20);
        textField.addActionListener(e -> {
            String text = textField.getText();
            textField.setText("");
            StringReader sr = new StringReader(text);
            Parser p = new Parser(sr);
            Command comando = p.getCombinazione().interpreta(gpanel,text);
            handler.handle(comando);

        });
        toolbar.add(textField);

        f.add(toolbar, BorderLayout.NORTH);

        JScrollPane jScrollPane = new JScrollPane(gpanel);
        jScrollPane.setVerticalScrollBarPolicy(jScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(jScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        f.add(jScrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());

        f.setTitle("Minicad");
        f.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

    }
}
