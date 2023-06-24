package blackjack.domain.player

class Dealer : GamePlayer() {
    private var isReceivedAddCard = false
    override fun isReceivable(): Boolean {
        return !isReceivedAddCard && cards.getOptimizedScore() <= RECEIVABLE_SCORE
    }

    override fun getDefaultStatus(): PlayerStatus {
        return PlayerStatus.NOT_INIT
    }

    override fun afterEventOfReceiveCard() {
        if (!isReceivedAddCard) isReceivedAddCard = true
    }

    companion object {
        const val RECEIVABLE_SCORE = 16
    }
}
