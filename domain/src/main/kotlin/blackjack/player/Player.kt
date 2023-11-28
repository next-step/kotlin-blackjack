package blackjack.player

import action.BlackJackAction
import blackjack.BlackjackParticipant
import blackjack.card.Card
import blackjack.hand.Hand
import blackjack.hand.StandardHand

data class Player(
    val name: String,
    private val hand: Hand = StandardHand()
) : BlackjackParticipant {

    val cards: List<Card> get() = hand.cards()

    fun canHit(): BlackJackAction = if (hand.calculateMinValue() <= 21) {
        BlackJackAction.HIT
    } else {
        BlackJackAction.STAND
    }

    override fun receiveCard(card: Card): Player = copy(hand = hand.addCard(card))

    override fun receiveCard(cards: List<Card>): Player = copy(hand = hand.addCard(cards))

    override fun calculateBestValue(): Int = hand.calculateBestValue()
}
