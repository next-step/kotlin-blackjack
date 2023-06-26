package blackjack.domain.player

import blackjack.domain.card.Card

class Player(val name: Name = Name(), val betAmount: PlayerBetAmount = PlayerBetAmount()) : GamePlayer() {

    override fun isReceivable(): Boolean {
        return status.isReceivable
    }

    override fun receiveCard(card: Card) {
        if (!isReceivable()) return
        cards.addCard(card)
        updateStatus()
    }

    fun setStayStatus() {
        status = PlayerStatus.STAY
    }
}
