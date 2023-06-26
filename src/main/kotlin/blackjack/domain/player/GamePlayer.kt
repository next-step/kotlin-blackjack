package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class GamePlayer {
    val cards: Cards = Cards()
    protected var status: PlayerStatus

    init {
        status = PlayerStatus.NOT_INIT
    }

    abstract fun isReceivable(): Boolean
    abstract fun receiveCard(card: Card)

    fun initCards(cards: Cards) {
        this.cards.addCards(cards)
        updateStatus()
    }

    fun getPlayerStatus(): PlayerStatus {
        return status
    }

    protected fun updateStatus() {
        val optimizedScore = cards.getOptimizedScore()
        val isReceivable = isReceivable()
        status = PlayerStatus.valuesOf(optimizedScore, isReceivable)
    }
}
