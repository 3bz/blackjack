import java.util.Scanner;

public class Input {
    Scanner scn;

    public Input()
    {
        scn = new Scanner(System.in);
    }

    public int userInput()
    {
    int result = scn.nextInt();
    return result;
    }
}
