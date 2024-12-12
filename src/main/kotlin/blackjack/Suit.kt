package blackjack

enum class Suit(val koreanName: String) {
    SPADES("스페이드"),
    CLUBS("클로버"),
    DIAMONDS("다이아몬드"),
    HEARTS("하트"), ;

    fun generateAllCards(): List<Card> {
        return Denomination.map { Card(this, it) }
    }

    companion object {
        fun flatMap(transform: (Suit) -> Iterable<Card>): List<Card> {
            return Suit.entries.flatMap(transform)
        }
    }
}
