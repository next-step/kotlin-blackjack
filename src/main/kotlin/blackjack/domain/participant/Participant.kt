package blackjack.domain.participant

import blackjack.domain.card.Card

abstract class Participant {
    abstract val name: String
    abstract val money: Money
    val hand: Hand = Hand()
    var status: ParticipantStatus = ParticipantStatus.HIT
        private set

    abstract fun isDrawable(): Boolean

    fun addCards(vararg cards: Card) {
        cards.forEach { hand.add(it) }
        if (hand.isBlackjack()) {
            changeStatus(ParticipantStatus.BLACKJACK)
        }
        if (hand.isBust()) {
            changeStatus(ParticipantStatus.BUST)
        }
    }

    fun score(): Int {
        return hand.score()
    }

    fun changeStatus(status: ParticipantStatus) {
        this.status = status
    }
}
