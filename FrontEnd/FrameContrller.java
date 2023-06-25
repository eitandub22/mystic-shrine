package FrontEnd;

import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class FrameContrller extends JFrame implements KeyListener {
    private LinkedList<Character> buffer;


    private JLabel label;

    public FrameContrller()
    {
        super("MS-controller");
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.label = new JLabel("Focus me, than input");
        setSize(250, 100);
        setPreferredSize(new Dimension(250, 100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(label);
        pack();
        setVisible(true);
        buffer = new LinkedList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        buffer.add(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ;
    }

    public char readBuffer()
    {
        char toRet = buffer.size() > 0 ? buffer.remove(0) : 'E';
        return toRet;
    }
}
