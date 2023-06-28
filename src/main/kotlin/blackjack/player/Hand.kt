package blackjack.player

import blackjack.card.Card

class Hand(
    private val cardList: MutableList<Card> = mutableListOf()
) {

    fun addCard(card: Card) {
        cardList.add(card)
    }

    val size: Int
        get() = cardList.size

    val displayCards: String
        get() = cardList.joinToString(", ") { it.toString() }

    fun forEachCard(block: (Card) -> Unit) {
        cardList.forEach(block)
    }
}
