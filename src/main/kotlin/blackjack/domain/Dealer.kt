package blackjack.domain

import blackjack.domain.Cards.Companion.CARD_DECK

class Dealer(
    cards: Cards = CARD_DECK,
) {
    val deck: Cards = cards.value
        .map { Card(it.type, it.shape) }
        .toMutableList()
        .let(::Cards)
}
