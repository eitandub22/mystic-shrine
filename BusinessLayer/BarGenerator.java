package BusinessLayer;

public class BarGenerator {
    public enum Color {
        //Color end string, color reset
        RESET("\033[0m"),

        // Regular Colors. Normal color, no bold, background color etc.
        BLACK("\033[0;30m"),
        RED("\033[0;31m"),
        GREEN("\033[0;32m"),
        YELLOW("\033[0;33m"),
        BLUE("\033[0;34m"),
        WHITE("\033[0;37m"),

        // Background
        RED_BACKGROUND("\033[41m"),
        GREEN_BACKGROUND("\033[42m"),
        YELLOW_BACKGROUND("\033[43m"),
        BLUE_BACKGROUND("\033[44m"),
        WHITE_BACKGROUND("\033[47m");

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public BarGenerator(){
        ;
    }

    public String genBar(int currAmount, int pool, char c, Color color)
    {
        StringBuilder sb = new StringBuilder();
        int numBars = (int)Math.ceil(((double)currAmount / pool) * 10);
        int i = 0;

        sb.append("[");
        sb.append(color);
        for(i = 0; i < numBars; i++)
        {
            sb.append(c);
        }
        sb.append(Color.RESET);
        for (i = i; i < 10; i++)
        {
            sb.append(" ");
        }
        sb.append(']');

        return sb.toString();
    }
}
