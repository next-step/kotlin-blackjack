package blackjack.domain.deck

import blackjack.domain.card.Card

fun interface DrawStrategy {
    fun draw(cards: MutableSet<Card>): Card
}
