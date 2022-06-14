package blackjack.domain

abstract class Participant {

    abstract val playerCards: Cards
    abstract val battingAmount: Int
    abstract val name: String
    private var _earnAmount = 0
    private var _initIsBlackJack = false
    val initIsBlackJack
        get() = _initIsBlackJack
    val earnAmount
        get() = _earnAmount

    abstract fun isDealer(): Boolean

    fun appendEarnAmount(amount: Int) {
        _earnAmount += amount
    }

    fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }

    fun setFirstDistributionBlackJack() {
        _initIsBlackJack = playerCards.initContributionCardIsBlackJack()
    }

    fun isBust(): Boolean {
        return this.playerCards.isBust()
    }

    fun isHit(): Boolean {
        return this.playerCards.isHit()
    }
}
