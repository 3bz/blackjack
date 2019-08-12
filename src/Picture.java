public enum Picture {
    Jack(10),
    Queen(10),
    King(10),
    Ace(11);
    private int value;

    Picture(int value)
    {
        this.value = value;
    }

    public int getValue() { return value; }
}
