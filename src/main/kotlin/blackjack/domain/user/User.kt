package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

class User(val name: String) {
    private val hand = Hand()

    fun addCardToHand(card: Card) = hand.addCardToHand(card)

    fun isHandAddable() = hand.isAddAble()

    fun getCards() = hand.getCards()
}
