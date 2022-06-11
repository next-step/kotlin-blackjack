package blackjack.view

import blackjack.domain.card.Card

object CardView {

    fun cardsToString(cards: List<Card>): List<String> {
        return cards.map {
            cardToString(it)
        }
    }

    private fun cardToString(card: Card): String {
        return card.number + card.pattern
    }
}
