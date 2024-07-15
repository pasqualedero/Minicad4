package exception;

import javax.swing.*;

public class InvalidID extends RuntimeException{
    public InvalidID(String msg){
        super(msg);
        JOptionPane.showMessageDialog(null, msg,
                "Qualcosa va storto...", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/error.png")));

    }
}
