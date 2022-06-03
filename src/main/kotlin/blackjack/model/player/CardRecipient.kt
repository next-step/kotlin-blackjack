package blackjack.model.player

import blackjack.model.card.Card

interface CardRecipient {
    fun addCard(card: Card)
}
