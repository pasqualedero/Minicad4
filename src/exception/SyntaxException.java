package exception;

import javax.swing.*;

public class SyntaxException extends RuntimeException {
    public SyntaxException() {
    }

    public SyntaxException(String msg) {
        super(msg);
        JOptionPane.showMessageDialog(null, msg,
                "Qualcosa va storto...", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/utility/error.png")));

    }
}
