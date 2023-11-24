package blackjack.player

import action.BlackJackAction
import blackjack.BlackjackParticipant
import blackjack.card.Card
import blackjack.hand.Hand
import blackjack.hand.StandardHand

class Player(
    val name: String,
    override val hand: Hand = StandardHand(),
) : BlackjackParticipant {

    val cards: List<Card>
        get() = hand.cards.toList()

    fun canHit(): BlackJackAction = if (hand.calculateMinValue() <= 21) {
        BlackJackAction.HIT
    } else {
        BlackJackAction.STAND
    }

    override fun receiveCard(card: Card): Player {
        return Player(name, hand = hand.addCard(card))
    }

    override fun calculateBestValue(): Int = hand.calculateBestValue()
}
