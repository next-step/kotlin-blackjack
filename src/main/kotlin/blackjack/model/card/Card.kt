package blackjack.model.card

class Card(val suit: Suit, val denomination: Denomination) {

    enum class Suit(val title: String) {
        CLUBS("클로버"),
        DIAMONDS("다이아몬드"),
        HEARTS("하트"),
        SPADES("스페이드")
    }

    enum class Denomination(val title: String, val point: Int, val pointOptional: Int) {
        ACE("A", 1, 11),
        TWO("2", 2, 2),
        THREE("3", 3, 3),
        FOUR("4", 4, 4),
        FIVE("5", 5, 5),
        SIX("6", 6, 6),
        SEVEN("7", 7, 7),
        EIGHT("8", 8, 8),
        NINE("9", 9, 9),
        TEN("10", 10, 10),
        JACK("J", 10, 10),
        QUEEN("Q", 10, 10),
        KING("K", 10, 10)
    }
}



