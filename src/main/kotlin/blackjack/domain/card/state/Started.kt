package blackjack.domain.card.state

import blackjack.domain.card.PlayingCards

abstract class Started : State {
    private val cards = PlayingCards(listOf())

    override fun cards(): PlayingCards {
        return cards
    }
}
