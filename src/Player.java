import java.util.ArrayList;

public class Player {
    private ArrayList<Card> myHand;
    private boolean isAlive;
    private boolean hasSat;

    public Player()
    {
        myHand = new ArrayList<>();
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

    public boolean checkBlackjack()
    {
        if (myHand.size() == 2 && Integer.parseInt(readHandTotal()) == 21)
            return true;
        return false;
    }

    public String readHandTotal()
    {
        int acesInHand = 0;
        int handTotal = 0;

        for (Card c : myHand) {
            if (c.getName() == "Ace")
                    acesInHand++;
                handTotal += c.getValue();
        }
        if (handTotal > 21) {
            if (acesInHand == 0)
                return "Bust!";
            else if (acesInHand > 0 && (handTotal - (10 * acesInHand)) > 21 )
                return "Bust!";
            else
                return String.valueOf(handTotal - (10 * acesInHand));
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
}
