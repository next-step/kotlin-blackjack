package blackjack.player

import blackjack.card.Card

class Player(
    val name: String,
    val cardList: ArrayList<Card> = ArrayList()
) {
    fun addCard(card: Card) {
        cardList.add(card)
    }
}
