package blackjack.domain.participant.state

import blackjack.domain.deck.Card

sealed class AfterInit(val cards: Cards) : State {

    override fun cards(): List<Card> = cards.values.toList()

    override fun score(): Score = cards.score()
}
