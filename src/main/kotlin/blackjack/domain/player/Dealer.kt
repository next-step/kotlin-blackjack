package blackjack.domain.player

import blackjack.domain.card.Card

class Dealer : GamePlayer() {
    val revenue: DealerRevenue = DealerRevenue()
    private var isReceivedAddCard = false

    override fun isReceivable(): Boolean {
        return !isReceivedAddCard && cards.getOptimizedScore() <= RECEIVABLE_SCORE
    }

    override fun receiveCard(card: Card) {
        if (!isReceivable()) return
        cards.addCard(card)
        setIsReceivedAddCardToTrue()
        updateStatus()
    }

    private fun setIsReceivedAddCardToTrue() {
        if (!isReceivedAddCard) isReceivedAddCard = true
    }

    companion object {
        const val RECEIVABLE_SCORE = 16
    }
}
