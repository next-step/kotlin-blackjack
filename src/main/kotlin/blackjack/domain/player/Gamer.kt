package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

class Gamer(name: PlayerName, hand: Hand = Hand()) : Player(name, hand) {
    override fun addCardToHand(card: Card) = hand.addCardToHand(card)

    override fun isHandAddable() = hand.canAdd()
}
