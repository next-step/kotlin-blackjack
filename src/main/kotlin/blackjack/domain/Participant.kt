package blackjack.domain

open class Participant(
    open val name: String,
    open val playerCards: Cards = Cards(),
    val gameScore: GameScore = GameScore()
) {
    open fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }

    open fun isDealer(): Boolean {
        return this is Dealer
    }

    fun isBust(): Boolean {
        return playerCards.score() < BLACK_JACK_SCORE
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
