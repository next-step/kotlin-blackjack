package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

class Player(val name: PlayerName) {
    private val hand = Hand()

    fun addCardToHand(card: Card) = hand.addCardToHand(card)

    fun isHandAddable() = hand.isAddAble()

    fun getCards() = hand.getCards()

    fun getHandValues() = hand.getValues()
}
