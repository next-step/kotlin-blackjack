package domain.state

import domain.card.Cards

abstract class ProceedingState(private val cards: Cards) : State {

    final override fun getCards(): Cards = cards
}
