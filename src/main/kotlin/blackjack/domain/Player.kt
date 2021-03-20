package blackjack.domain

internal abstract class Player(val name: String) {
    private val playerCards: PlayerCards = PlayerCards()

    abstract fun canHit(): Boolean
    abstract val visibleCards: List<Card>

    val cards: List<Card>
        get() = this.playerCards.cards

    fun acceptCard(card: Card) {
        this.playerCards.add(card)
    }

    fun score(): Int {
        return playerCards.score()
    }
}
