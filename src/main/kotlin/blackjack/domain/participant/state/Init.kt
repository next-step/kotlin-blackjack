package blackjack.domain.participant.state

import blackjack.domain.deck.Card

object Init {
    fun receiveCard(firstCard: Card, secondCard: Card): State {
        val cards = Cards(mutableListOf(firstCard, secondCard))
        return when (cards.isBlackjackScore) {
            true -> Blackjack(cards = cards)
            false -> Hit(cards = cards)
        }
    }
}
