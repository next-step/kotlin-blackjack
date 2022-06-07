package blackjack.domain

data class Card(
    val pattern: CardPattern,
    val denomination: Denomination
) {
    enum class CardPattern {
        SPADES,
        DIAMONDS,
        HEARTS,
        CLUBS;
    }

    enum class Denomination(val displayName: String, val point: Int) {
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
        JACK("J", 10),
        QUEEN("Q", 10),
        KING("K", 10);

        companion object {
            fun of(pattern: CardPattern): List<Card> {
                return values().map { Card(pattern, it) }
            }
        }
    }

    companion object {
        fun createDeck(): List<Card> {
            return CardPattern.values()
                .flatMap { Denomination.of(it) }
                .shuffled()
        }
    }
}
