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

        int startingHandSize = 2;
        for (int i = 0; i < startingHandSize; i++)
            myPlayer.hit(myDeck.dealCard());
        for (int i = 0; i < startingHandSize; i++)
            myDealer.hit(myDeck.dealCard());
    }
    public static void runGame()
    {
        if (myPlayer.checkHasBlackjack())
            playerVersusDealerBlackjack();

        playerTurn();

        if (! (myPlayer.isAlive()) )
            dealerWins();

        dealerTurn();

        if (! (myDealer.isAlive()) )
            playerWins();

        if (checkDealerScoreIsHigher())
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
        int dealerWontSitBelowThreshold = 17;
        while (! (myDealer.hasSat()) )
        {
            readOutPlayerHand(myDealer);

            try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt();}

            if (Integer.parseInt(myDealer.calculateHandTotal()) > dealerWontSitBelowThreshold)
                myDealer.stay();
            else {
                playerHits(myDealer);
                try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                if (!(checkPlayerIsAlive(myDealer)))
                    break;
            }
        }
    }

    public static void playerVersusDealerBlackjack()
    {
        System.out.println("Player has Blackjack, with hand [" + myPlayer.readCardsInHand() + "]\n");
        try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt();}

        boolean isADraw = myDealer.checkHasBlackjack();
        System.out.println("Dealer has the hand [" + myDealer.readCardsInHand() + "]\n");

        if (isADraw) {
            tieGame();
        }
        playerWins();
    }

    public static void readOutPlayerHand(Player aPlayer)
    {
        System.out.println( aPlayer.getPlayerName() + " is currently at " + aPlayer.calculateHandTotal());
        System.out.println("with the hand [" + aPlayer.readCardsInHand() + "]\n");
    }
    
    public static boolean checkPlayerIsAlive(Player aPlayer)
    {
        if (aPlayer.calculateHandTotal().equals("Bust!")) {
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

    public static boolean checkDealerScoreIsHigher()
    {
        return ( Integer.parseInt(myDealer.calculateHandTotal()) >= Integer.parseInt(myPlayer.calculateHandTotal()) );
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
