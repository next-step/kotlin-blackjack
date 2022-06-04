package blackjack.domain

data class Card(
    val pattern: CardPattern,
    val score: CardDisplayValue
) {
    enum class CardPattern(val displayName: String) {
        SPADES("스페이드"),
        DIAMONDS("다이아몬드"),
        HEARTS("하트"),
        CLUBS("클로버");
    }

    enum class CardDisplayValue(val displayName: String, val point: Int) {
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
                return CardDisplayValue.values().map { Card(pattern, it) }
            }
        }
    }

    companion object {
        fun createDeck(): List<Card> {
            return ArrayList<Card>().apply {
                CardPattern.values().forEach {
                    this.addAll(CardDisplayValue.of(it))
                }
            }.shuffled().toList()
        }
    }
}
