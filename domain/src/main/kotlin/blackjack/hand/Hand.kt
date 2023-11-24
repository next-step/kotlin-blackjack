package blackjack.hand

import blackjack.card.Card

interface Hand {
    val cards: Set<Card>
    fun addCard(card: Card): Hand
    fun calculateMinValue(): Int
    fun calculateBestValue(): Int
}
