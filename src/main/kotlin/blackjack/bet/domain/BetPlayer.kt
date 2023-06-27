package blackjack.bet.domain

import blackjack.common.domain.Player
import blackjack.common.service.DeckManager

open class BetPlayer(name: String) : Player<BetPlayer>(name) {

    private val wallet = Wallet()

    fun wallet(): Wallet {
        return this.wallet
    }

    fun chargeWallet(money: Int) {
        wallet.charge(money)
    }

    fun isInitialBlackjack(optimalValue: Int): Boolean {
        isPlaying = optimalValue != BLACK_JACK_NUMBER
        return isPlaying
    }

    fun isPlaying(): Boolean {
        return isPlaying
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
