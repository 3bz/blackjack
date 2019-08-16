public class Game {
    private Player myPlayer;
    private Player myDealer;
    private Deck myDeck;
    private Output myOut;
    private Input myIn;

    public Game(Player aPlayer, Player aDealer, Deck aDeck, Input aInput)
    {
        myPlayer = aPlayer;
        myDealer = aDealer;
        myDeck = aDeck;
        myOut = new Output();
        myIn = aInput;
    }

    public void start()
    {
        myDeck.shuffle();

        int startingHandSize = 2;
        for (int i = 0; i < startingHandSize; i++)
            myPlayer.hit(myDeck.dealCard());
        for (int i = 0; i < startingHandSize; i++)
            myDealer.hit(myDeck.dealCard());

        runGame();
    }

    private void runGame()
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

    private void playerTurn()
    {
        while (! (myPlayer.hasSat()) )
        {
            readOutPlayerHand(myPlayer);

            if (! (checkPlayerIsAlive(myPlayer)) )
                break;

            playerHitOrStay(myPlayer);
        }
    }

    private void dealerTurn() {
        int dealerWontSitBelowThreshold = 17;
        while (! (myDealer.hasSat()) )
        {
            readOutPlayerHand(myDealer);

            try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt();}

            if (Integer.parseInt(myDealer.calculateHandTotal()) >= dealerWontSitBelowThreshold)
                myDealer.stay();
            else {
                playerHits(myDealer);
                try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                if (!(checkPlayerIsAlive(myDealer)))
                    break;
            }
        }
    }

    private void playerVersusDealerBlackjack()
    {
        myOut.outMessage("Player has Blackjack, with hand [" + myPlayer.readCardsInHand() + "]\n");
        try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt();}

        boolean isADraw = myDealer.checkHasBlackjack();
        myOut.outMessage("Dealer has the hand [" + myDealer.readCardsInHand() + "]\n");

        if (isADraw) {
            tieGame();
        }
        playerWins();
    }

    private void readOutPlayerHand(Player aPlayer)
    {
        myOut.outMessage(aPlayer.getPlayerName() + " is currently at " + aPlayer.calculateHandTotal());
        myOut.outMessage("with the hand [" + aPlayer.readCardsInHand() + "]\n");
    }

    private boolean checkPlayerIsAlive(Player aPlayer)
    {
        if (aPlayer.calculateHandTotal().equals("Bust!")) {
            aPlayer.setIsAlive(false);
            return false;
        }
        return true;
    }

    private void playerHitOrStay(Player aPlayer)
    {
        myOut.outMessage("Hit or Stay? (Hit = 1, Stay = 0): ");
        int playerInput = myIn.userInput();

        switch (playerInput) {
            case (1):
                playerHits(aPlayer);
                break;
            case (0):
                aPlayer.stay();
                myOut.newLine();
                break;
        }
    }

    private void playerHits(Player aPlayer)
    {
        aPlayer.hit(myDeck.dealCard());
        myOut.outMessage(aPlayer.getPlayerName() + " draws " + aPlayer.readLastCard() + "\n");
    }

    private boolean checkDealerScoreIsHigher()
    {
        return ( Integer.parseInt(myDealer.calculateHandTotal()) >= Integer.parseInt(myPlayer.calculateHandTotal()) );
    }

    private void playerWins()
    {
        myOut.outMessage("You beat the dealer!");
        System.exit(0);
    }

    private void dealerWins()
    {
        myOut.outMessage("Dealer wins!");
        System.exit(0);
    }

    private void tieGame()
    {
        myOut.outMessage("We have a tie game!");
        System.exit(0);
    }
}
