package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

object Started {
    fun handCard(firstCard: Card, secondCard: Card): State {
        val cards = addCards(firstCard, secondCard)
        return when (cards.isBlackjack()) {
            true -> Blackjack(cards = cards)
            false -> Hit(cards = cards)
        }
    }

    private fun addCards(
        firstCard: Card,
        secondCard: Card
    ): Cards {
        return Cards().apply {
            add(firstCard)
            add(secondCard)
        }
    }
}
