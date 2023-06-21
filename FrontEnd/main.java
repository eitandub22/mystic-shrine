package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class main {
    public static void main(String[] args) throws IOException {
        CLI cli = new CLI(args[0]);
        cli.getPlayer();
    }

}
