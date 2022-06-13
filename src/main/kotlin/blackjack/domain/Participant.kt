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
        return false
    }

    open fun isBust(): Boolean = playerCards.score() > BLACK_JACK_SCORE

    fun isHit(): Boolean {
        return this.playerCards.score() <= SCORE_TO_REQUEST_A_CARD_FOR_DEALER
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val SCORE_TO_REQUEST_A_CARD_FOR_DEALER = 16
    }
}
