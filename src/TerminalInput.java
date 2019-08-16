import java.util.Scanner;

public class TerminalInput implements Input {
    Scanner scn;

    public TerminalInput()
    {
        scn = new Scanner(System.in);
    }

    public int userInput()
    {
    int result = scn.nextInt();
    return result;
    }
}
