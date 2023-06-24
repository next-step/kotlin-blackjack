package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class GamePlayer {
    val cards: Cards = Cards()
    protected var status: PlayerStatus

    init {
        status = getDefaultStatus()
    }

    abstract fun isReceivable(): Boolean
    abstract fun getDefaultStatus(): PlayerStatus

    abstract fun afterEventOfReceiveCard()

    fun initCards(cards: Cards) {
        this.cards.addCards(cards)
        this.cards.updateScoreSet(cards)
        updateStatus()
    }

    fun receiveCard(card: Card) {
        if (!isReceivable()) return
        cards.addCard(card)
        cards.updateScoreSet(card)
        afterEventOfReceiveCard()
        updateStatus()
    }

    fun getPlayerStatus(): PlayerStatus {
        return status
    }

    private fun updateStatus() {
        val optimizedScore = cards.getOptimizedScore()
        status = PlayerStatus.valuesOf(optimizedScore) { isReceivable() }
    }
}
