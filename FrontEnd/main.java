package FrontEnd;

import java.io.IOException;


public class main {
    public static void main(String[] args) throws IOException {
        CLI cli = new CLI(args[0]);
        cli.getPlayer();
        cli.close();
    }

}
