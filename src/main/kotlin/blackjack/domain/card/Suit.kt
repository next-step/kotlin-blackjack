package blackjack.domain.card

enum class Suit(val symbol: String) {
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣"),
    SPADES("♠"),
    ;

    fun createCards(): List<PlayingCard> {
        return Denomination.entries.map { denomination ->
            PlayingCard(this, denomination)
        }
    }
}
