package blackjack.ui

import blackjack.domain.card.Card

object CardPrinter {
    fun message(cards: List<Card>): String = cards.joinToString(
        separator = ", ",
        transform = { displayCard(it) }
    )

    private fun displayCard(card: Card) = "${card.cardNumber.displayName}${card.cardSuit.displayName}"

}
