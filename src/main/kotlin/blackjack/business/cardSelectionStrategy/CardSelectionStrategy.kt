package blackjack.business.cardSelectionStrategy

import blackjack.business.card.Card

fun interface CardSelectionStrategy {
    fun selectCard(cards: List<Card>): Card
}
