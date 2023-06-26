package blackjack.domain.player

import blackjack.domain.card.Card

class Player(val name: Name = Name()) : GamePlayer() {

    override fun isReceivable(): Boolean {
        return status.isReceivable
    }

    override fun receiveCard(card: Card) {
        if (!isReceivable()) return
        cards.addCard(card)
        updateStatus()
    }

    fun getStayStatus() {
        status = PlayerStatus.STAY
    }
}
