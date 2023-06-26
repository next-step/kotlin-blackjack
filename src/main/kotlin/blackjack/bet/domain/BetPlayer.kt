package blackjack.bet.domain

import blackjack.common.domain.Player
import blackjack.common.service.DeckManager

open class BetPlayer(name: String) : Player<BetPlayer>(name) {

    private val wallet = Wallet()
    private var isNotPlayingRound = true

    fun wallet(): Wallet {
        return this.wallet
    }

    fun chargeWallet(money: Int) {
        wallet.charge(money)
    }

    fun isInitialBlackjack(): Boolean {
        if (this.optimalValue() == BLACK_JACK_NUMBER) {
            isNotPlayingRound = true
        }
        return isNotPlayingRound
    }

    fun isPlayingRound(): Boolean {
        return !isNotPlayingRound
    }

    override fun drawPhase(deckManager: DeckManager, handNotice: (BetPlayer) -> Unit) {
        if (isPlayingRound()) {
            hit(deckManager.draw())
            handNotice.invoke(this)
        }
    }

    companion object {
        private const val BLACK_JACK_NUMBER = 21
    }
}
