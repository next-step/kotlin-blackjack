package blackjack.strategy.assign

import blackjack.domain.card.Deck
import blackjack.domain.card.Card

fun interface AssignCardStrategy {
    fun assign(deck: Deck): List<Card>
}
