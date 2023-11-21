package blackjack.business.participants

import blackjack.business.util.BlackJackConst
import blackjack.business.util.Money

class GamePlayer(
    name: String, playerCards: PlayerCards = PlayerCards(), private val money: Money = Money(MIN_BETTING_MONEY)
) : Player(name, playerCards) {

    override fun canDrawCard(): Boolean = playerCards.canDrawCardWithValueLimit(BlackJackConst.BLACKJACK)

    fun calculateMultiplierResult(multiplier: Double): Int = money.calculateMultiplierResult(multiplier)
    fun lose(): Int = money.reverseValue()
    fun getScoreDifferent(score: Int): Int = this.score - score

    companion object {
        const val MIN_BETTING_MONEY = 0
    }
}
