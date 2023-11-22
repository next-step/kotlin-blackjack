package blackjack.player

import blackjack.card.Card
import blackjack.hand.Hand

data class Player(
    val name: String,
    private val hand: Hand,
) {
    val cards: List<Card>
        get() = hand.cards

    fun canReceiveCard(): Boolean = hand.calculateBestValue() <= 21
    fun receiveCard(card: Card): Player {
        return copy(hand = hand.addCard(card))
    }
    fun calculateBestValue(): Int = hand.calculateBestValue()
}
