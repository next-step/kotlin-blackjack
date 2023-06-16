package blackjack.domain.deck

import blackjack.domain.card.Card

object DeckGenerator {

    fun generator(): ArrayDeque<Card> = Denomination.values()
        .flatMap(::createCardsBySuit)
        .shuffled()
        .run(::ArrayDeque)

    private fun createCardsBySuit(denomination: Denomination): List<Card> = Suit.values()
        .map { Card(denomination = denomination, suit = it) }
}
