public class Game {
    private Player myPlayer;
    private Player myDealer;
    private Deck myDeck;
    private Output myOut;
    private Input myIn;

    public Game(Player aPlayer, Player aDealer, Deck aDeck)
    {
        myPlayer = aPlayer;
        myDealer = aDealer;
        myDeck = aDeck;
        myOut = new Output();
        myIn = new Input();
    }

    public void runGame()
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

    public void playerTurn()
    {
        while (! (myPlayer.hasSat()) )
        {
            readOutPlayerHand(myPlayer);

            if (! (checkPlayerIsAlive(myPlayer)) )
                break;

            playerHitOrStay(myPlayer);
        }
    }

    public void dealerTurn() {
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

    public void playerVersusDealerBlackjack()
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

    public void readOutPlayerHand(Player aPlayer)
    {
        myOut.outMessage(aPlayer.getPlayerName() + " is currently at " + aPlayer.calculateHandTotal());
        myOut.outMessage("with the hand [" + aPlayer.readCardsInHand() + "]\n");
    }

    public boolean checkPlayerIsAlive(Player aPlayer)
    {
        if (aPlayer.calculateHandTotal().equals("Bust!")) {
            aPlayer.setIsAlive(false);
            return false;
        }
        return true;
    }

    public void playerHitOrStay(Player aPlayer)
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

    public void playerHits(Player aPlayer)
    {
        aPlayer.hit(myDeck.dealCard());
        myOut.outMessage(aPlayer.getPlayerName() + " draws " + aPlayer.readLastCard() + "\n");
    }

    public boolean checkDealerScoreIsHigher()
    {
        return ( Integer.parseInt(myDealer.calculateHandTotal()) >= Integer.parseInt(myPlayer.calculateHandTotal()) );
    }

    public void playerWins()
    {
        myOut.playerWins();
    }

    public void dealerWins()
    {
        myOut.dealerWins();
    }

    public void tieGame()
    {
        myOut.tieGame();
    }
}
