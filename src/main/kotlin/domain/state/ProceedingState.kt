package domain.state

import domain.card.BlackjackCards

abstract class ProceedingState(private val cards: BlackjackCards) : State {

    final override fun isDrawable(): Boolean = cards.isDrawable()

    final override fun getCards(): BlackjackCards = cards

    final override fun isProceeding(): Boolean = true
    final override fun getScoreSum(): Int = cards.sum
}
