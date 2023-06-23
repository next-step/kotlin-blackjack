package blackjack.player

import blackjack.card.Card

class Hand(
    private val cardList: ArrayList<Card> = ArrayList()
) {
    fun addCard(card: Card) {
        cardList.add(card)
    }
}
