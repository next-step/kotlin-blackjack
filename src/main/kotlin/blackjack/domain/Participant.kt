package blackjack.domain

open class Participant(
    open val name: String,
    open val playerCards: Cards = Cards(),
    val gameScore: GameScore = GameScore()
) {
    private var _battingAmount = 0
    val battingAmount
        get() = _battingAmount

    open fun battingMoney(amount: Int) {
        this._battingAmount = amount
    }

    open fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }

    open fun isDealer(): Boolean {
        return false
    }

    fun isHit(): Boolean {
        return this.playerCards.score() < SCORE_TO_REQUEST_A_CARD_FOR_DEALER
    }

    companion object {
        private const val SCORE_TO_REQUEST_A_CARD_FOR_DEALER = 16
    }
}
