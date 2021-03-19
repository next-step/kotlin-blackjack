package blackjack.domain

internal class Player(val name: String) {
    private val playerCards: PlayerCards = PlayerCards()

    val cards: List<Card>
        get() = this.playerCards.cards

    fun acceptCard(card: Card) {
        this.playerCards.add(card)
    }

    fun canHit(): Boolean {
        return score() < BlackJackGame.MAX_SCORE
    }

    fun score(): Int {
        return playerCards.score()
    }
}
