public class Output {

    public Output() {}

    public void playerWins()
    {
        System.out.println("You beat the dealer!");
        System.exit(0);
    }

    public void dealerWins()
    {
        System.out.println("Dealer wins!");
        System.exit(0);
    }

    public void tieGame()
    {
        System.out.println("We have a tie game!");
        System.exit(0);
    }

    public void newLine()
    {
        System.out.print("\n");
    }

    public void outMessage(String aMessage)
    {
        System.out.println(aMessage);
    }
}
