package blackjack.domain

import blackjack.exception.DrawCardFailException

abstract class Participant(
    val name: String,
    val participantCards: Cards
) {
    var blackJackStatus = participantCards.getBlackJackStatus()
        private set

    abstract fun isDealer(): Boolean

    abstract fun canDrawable(): Boolean

    fun addCard(card: Card) {
        if (blackJackStatus.isDrawable) {
            this.participantCards.addCard(card)
            blackJackStatus = participantCards.getBlackJackStatus()
        } else {
            throw DrawCardFailException()
        }
    }

    fun setFirstDistributionBlackJack() {
        blackJackStatus = participantCards.getBlackJackStatus()
    }

    fun setBlackJackStatusStay() {
        blackJackStatus = BlackJackStatus.STAY
    }
}
