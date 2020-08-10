package blackjack



class Card(var rank: Rank, var suit: Suit) {

    public enum class Rank(var value: Int) {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);
    }

    enum class Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }
    /*
    companion object{
        fun from(findValue:Int): Rank{
            when (findValue){}
        }
    }
     */
}
