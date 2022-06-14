package blackjack.model.player

import blackjack.model.card.Card

fun interface CardRecipient {
    fun addCard(card: Card)
}
