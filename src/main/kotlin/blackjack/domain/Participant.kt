package blackjack.domain

open class Participant(
    open val name: String,
    open val playerCards: Cards = Cards()
) {
    private var _earnAmount = 0
    val earnAmount
        get() = _earnAmount
    private var _isBlackJack = false
    val isBlackJack
        get() = _isBlackJack

    private var _battingAmount = 0
    val battingAmount
        get() = _battingAmount

    fun appendEarnAmount(amount: Int) {
        _earnAmount += amount
    }

    fun setFirstDistributionBlackJack() {
        _isBlackJack =
            playerCards.score() == BLACK_JACK_SCORE && this.playerCards.playerCards.size == FIRST_DISTRIBUTION_CARD_COUNT
    }

    open fun isBust(): Boolean {
        return this.playerCards.isBust()
    }

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
        private const val BLACK_JACK_SCORE = 21
        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}
