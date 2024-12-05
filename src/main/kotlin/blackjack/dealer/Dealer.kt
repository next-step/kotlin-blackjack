package blackjack.dealer

import blackjack.card.Card
import blackjack.machine.BlackJackMachine
import blackjack.player.Hand

class Dealer(
    val name: String = "딜러",
    val hand: Hand = Hand(cards = emptyList()),
) {
    fun isBust(): Boolean = hand.sum() > BlackJackMachine.BLACKJACK

    fun isDraw(): Boolean = hand.sum() <= DEALER_STANDING_RULE

    fun hitCard(card: Card): Dealer = Dealer(name = name, hand = hand.add(card))

    companion object {
        const val DEALER_STANDING_RULE = 16

        fun ready(initialCards: List<Card>) = Dealer(hand = Hand(cards = initialCards))
    }
}

