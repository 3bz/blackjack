import java.util.Scanner;

public class Game {
    static Deck myDeck = new Deck();
    static Player myPlayer = new Player("Player 1");
    static Player myDealer = new Player("Dealer");
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        init();
        runGame();
    }
    public static void init() {
        myDeck.shuffle();

        for (int i = 0; i < 2; i++)
            myPlayer.hit(myDeck.dealCard());
        for (int i = 0; i < 2; i++)
            myDealer.hit(myDeck.dealCard());
    }
    public static void runGame()
    {
        if (myPlayer.checkBlackjack())
            playerHasBlackjack();

        playerTurn();

        if (! (myPlayer.isAlive()) )
            dealerWins();

        dealerTurn();

        if (! (myDealer.isAlive()) )
            playerWins();

        if ( Integer.parseInt(myDealer.readHandTotal()) >= Integer.parseInt(myPlayer.readHandTotal()) )
            dealerWins();

        playerWins();
    }

    public static void playerTurn()
    {
        while (! (myPlayer.hasSat()) )
        {
            readOutPlayerHand(myPlayer);

            if (! (checkPlayerIsAlive(myPlayer)) )
                break;

            playerHitOrStay(myPlayer);
        }
    }

    public static void dealerTurn() {
        while (! (myDealer.hasSat()) )
        {
            readOutPlayerHand(myDealer);

            try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt();}

            if (Integer.parseInt(myDealer.readHandTotal()) > 17)
                myDealer.stay();
            else {
                playerHits(myDealer);
                try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                if (!(checkPlayerIsAlive(myDealer)))
                    break;
            }
        }
    }

    public static void playerHasBlackjack()
    {
        System.out.println("Player has Blackjack, with hand [" + myPlayer.readCardsInHand() + "]\n");
        try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt();}

        boolean isADraw = myDealer.checkBlackjack();
        System.out.println("Dealer has the hand [" + myDealer.readCardsInHand() + "]\n");

        if (isADraw) {
            tieGame();
        }
        playerWins();
    }

    public static void readOutPlayerHand(Player aPlayer)
    {
        System.out.println( aPlayer.getPlayerName() + " is currently at " + aPlayer.readHandTotal());
        System.out.println("with the hand [" + aPlayer.readCardsInHand() + "]\n");
    }
    
    public static boolean checkPlayerIsAlive(Player aPlayer)
    {
        if (aPlayer.readHandTotal().equals("Bust!")) {
            aPlayer.setIsAlive(false);
            return false;
        }
        return true;
    }

    public static void playerHitOrStay(Player aPlayer)
    {
        int playerInput;
        System.out.print("Hit or Stay? (Hit = 1, Stay = 0): ");
        playerInput = scn.nextInt();

        switch (playerInput) {
            case (1):
                playerHits(aPlayer);
                break;
            case (0): //stay
                aPlayer.stay();
                System.out.println("\n");
                break;
        }
    }

    public static void playerHits(Player aPlayer)
    {
        aPlayer.hit(myDeck.dealCard());
        System.out.println(aPlayer.getPlayerName() + " draws " + aPlayer.readLastCard() + "\n");
    }

    public static void playerWins()
    {
        System.out.println("You beat the dealer!");
        System.exit(0);
    }

    public static void dealerWins()
    {
        System.out.println("Dealer wins!");
        System.exit(0);
    }

    public static void tieGame()
    {
        System.out.println("We have a tie game!");
        System.exit(0);
    }
}
