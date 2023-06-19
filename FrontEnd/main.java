package FrontEnd;

import BusinessLayer.Tiles.TileFactory;

import java.io.File;

public class main {
    public static void main(String[] args)
    {
        File inFile = null;
        if (0 < args.length) {
            inFile = new File(args[0]);
        } else {
            System.err.println("Invalid arguments count:" + args.length);
            System.exit(0);
        }
    }

}
