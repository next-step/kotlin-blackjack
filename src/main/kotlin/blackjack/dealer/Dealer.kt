package blackjack.dealer

import blackjack.card.Card
import blackjack.player.Hand
import blackjack.player.Player

class Dealer(
    name: String = "딜러",
    hand: Hand = Hand(cards = emptyList()),
) : Player(name = name, hand = hand) {
    fun isDraw(): Boolean = hand.sum() <= DEALER_STANDING_RULE

    override fun hitCard(card: Card): Dealer = Dealer(name = name, hand = hand.add(card))

    companion object {
        const val DEALER_STANDING_RULE = 16

        fun ready(initialCards: List<Card>) = Dealer(hand = Hand(cards = initialCards))
    }
}
