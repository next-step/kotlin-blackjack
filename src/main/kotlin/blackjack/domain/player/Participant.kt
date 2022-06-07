package blackjack.domain.player

import blackjack.domain.card.Card

open class Participant {
    val hand: Hand = Hand()
    private var status: PlayerStatus = PlayerStatus.HIT

    fun isDrawable(): Boolean {
        return status == PlayerStatus.HIT
    }

    fun addCards(vararg cards: Card) {
        cards.forEach { hand.add(it) }
        if (hand.isBust()) {
            changeStatus(PlayerStatus.BUST)
        }
    }

    fun score(): Int {
        return hand.score()
    }

    fun changeStatus(status: PlayerStatus) {
        this.status = status
    }
}
