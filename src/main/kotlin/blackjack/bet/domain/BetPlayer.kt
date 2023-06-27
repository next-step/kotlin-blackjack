package blackjack.bet.domain

import blackjack.common.domain.Player
import blackjack.common.service.DeckManager
import blackjack.common.service.HandsCalculator

open class BetPlayer(name: String) : Player<BetPlayer>(name) {

    private val wallet = Wallet()

    fun wallet(): Wallet {
        return this.wallet
    }

    fun charge(money: Int) {
        wallet.charge(money)
    }

    fun isInitialBlackjack(): Boolean {
        val initialOptimalValue = HandsCalculator.calculateOptimalValue(this.hands().subList(0, 2))
        return initialOptimalValue == BLACK_JACK_NUMBER
    }

    override fun drawPhase(deckManager: DeckManager, handNotice: (BetPlayer) -> Unit) {
        hit(deckManager.draw())
        handNotice.invoke(this)
    }

    override fun canDraw(): Boolean {
        return this.optimalValue() < BLACK_JACK_NUMBER
    }

    companion object {
        private const val BLACK_JACK_NUMBER = 21
    }
}
