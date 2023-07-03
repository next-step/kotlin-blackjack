package blackjack.domain.card

data class Card(val pattern: CardPattern, val number: CardNumber) {
    override fun toString(): String {
        return "${number.displayName}${pattern.displayName}"
    }
}

enum class CardPattern(val displayName: String) {
    Heart("하트"),
    Spade("스페이드"),
    Diamond("다이아"),
    Club("클로버"),
}

enum class CardNumber(val displayName: String, val value: Int) {
    A("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    companion object {
        val COUNT = CardNumber.values().count()
    }
}
