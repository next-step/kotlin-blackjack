package blackjack.domain.participant.state

import blackjack.domain.deck.Card

sealed class AfterInit(val hand: Hand) : State {

    override fun cards(): List<Card> = hand.cards.toList()

    override fun score(): Int = hand.getScoreValue()
}
