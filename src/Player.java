import java.util.ArrayList;

public class Player {
    private ArrayList<Card> myHand;
    private String playerName;
    private boolean isAlive;
    private boolean hasSat;

    public Player(String aName)
    {
        myHand = new ArrayList<>();
        playerName = aName;
        isAlive = true;
        hasSat = false;
    }

    public void hit(Card aCard)
    {
        myHand.add(aCard);
    }

    public void stay()
    {
        hasSat = true;
    }

    public boolean checkHasBlackjack()
    {
        int smallestPossibleHandSize = 2;
        int highestPossibleScore = 21;
        if (myHand.size() == smallestPossibleHandSize && Integer.parseInt(calculateHandTotal()) == highestPossibleScore)
            return true;
        return false;
    }

    public String calculateHandTotal()
    {
        int acesInHand = 0;
        int handTotal = 0;
        int highestPossibleScore = 21;

        for (Card c : myHand) {
            handTotal += c.getValue();
            if (c.getName() == "Ace")
            {
                acesInHand++;
            }
        }
        if (handTotal > highestPossibleScore) {
            if (acesInHand == 0)
                return "Bust!";
            else if (acesInHand > 0 && (handTotal - (10 * acesInHand)) > 21 )
                return "Bust!";
            else    //situations where player draws multiple aces, WAS always deducting both, instead of one when required.
                while (handTotal > highestPossibleScore && acesInHand > 0) {
                    handTotal -= 10;
                    acesInHand--;
                }
                return String.valueOf(handTotal);
        }
        return (String.valueOf(handTotal));
    }

    public String readCardsInHand()
    {
        String handNames = "";
        for (Card c: myHand)
        {
            handNames += "['" + c.getName() + "', '" + c.getSuit() +"']";
        }
        return handNames;
    }

    public String readLastCard()
    {
        return ("['" + myHand.get(myHand.size()-1).getName() + "', '" + myHand.get(myHand.size()-1).getSuit() +"']");
    }

    public boolean hasSat() {
        return hasSat;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    public String getPlayerName() {
        return playerName;
    }
}
