package blackjack.core.card

data class Card(val denomination: Denomination, val suit: Suit) {
    override fun toString(): String {
        return denomination.title + suit.title
    }

    companion object {
        val CARD_COUNT = Denomination.entries.size * Suit.entries.size
    }
}

enum class Denomination(val title: String, val score: Int) {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("Jack", 10),
    QUEEN("Queen", 10),
    KING("King", 10),
}

enum class Suit(val title: String) {
    CLUBS("클로버"),
    DIAMONDS("다이아"),
    HEARTS("하트"),
    SPADES("스페이드"),
}
