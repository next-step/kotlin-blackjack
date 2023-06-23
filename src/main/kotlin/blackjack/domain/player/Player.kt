package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Player(
    val name: Name = Name(),
) {
    val cards: Cards = Cards()
    private var status: PlayerStatus = PlayerStatus.RECEIVE

    init {
        updateStatus()
    }

    fun isReceivable(): Boolean {
        return status == PlayerStatus.RECEIVE || status == PlayerStatus.BLACK_JACK
    }

    fun isBlackJack(): Boolean {
        return status == PlayerStatus.BLACK_JACK
    }

    fun receiveCard(card: Card) {
        if (!isReceivable()) return
        cards.addCard(card)
        cards.updateScoreSet(card)
        updateStatus()
    }

    private fun updateStatus() {
        val optimizedScore = cards.getOptimizedScore()
        status = PlayerStatus.valuesOf(optimizedScore, status)
    }

    companion object {
        const val INIT_SCORE = 0
    }
}
