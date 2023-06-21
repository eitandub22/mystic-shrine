package FrontEnd;

public class FronEndCallbacks {
    private InputReader inputReader;
    private MessageCallback messageCallback;

    public FronEndCallbacks(CLI cli)
    {
        inputReader = () -> cli.readLine();
        messageCallback = (String m) -> cli.displayMessage(m);
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
