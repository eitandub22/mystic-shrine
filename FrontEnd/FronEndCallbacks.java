package FrontEnd;

public class FronEndCallbacks {
    private InputReader inputReader;
    private MessageCallback messageCallback;

    private GameOverCallback gameOverCallback;

    public FronEndCallbacks(CLI cli)
    {
        inputReader = () -> cli.readLine();
        messageCallback = (String m) -> cli.displayMessage(m);
        gameOverCallback = () -> cli.endGame();
    }

    public String readLine()
    {
        return inputReader.read();
    }
    public void displayMessage(String m)
    {
        this.messageCallback.send(m);
    }
}
