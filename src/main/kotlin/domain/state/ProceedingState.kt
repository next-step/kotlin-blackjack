package domain.state

import domain.card.BlackjackCards

abstract class ProceedingState(private val cards: BlackjackCards) : State {

    final override fun getCards(): BlackjackCards = cards
}
