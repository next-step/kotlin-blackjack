package participant

import Hand
import card.PlayingCard

class Dealer(override val hand: Hand) : Participant {
    override val name: String = "Dealer"

    override fun drawCards(cards: List<PlayingCard>) {
        hand.add(cards)
    }

    override fun showCardFirst(): List<PlayingCard> {
        return listOf(hand.cards.first())
    }
}
