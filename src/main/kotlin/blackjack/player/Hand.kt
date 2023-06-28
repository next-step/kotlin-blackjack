package blackjack.player

import blackjack.card.Card

class Hand(
    private val cardList: MutableList<Card> = mutableListOf()
) {

    fun addCard(card: Card) {
        cardList.add(card)
    }

    fun size(): Int {
        return cardList.size
    }

    fun displayCards(): String {
        return cardList.joinToString(", ") { it.toString() }
    }

    fun forEachCard(block: (Card) -> Unit) {
        cardList.forEach(block)
    }
}
