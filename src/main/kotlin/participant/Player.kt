package participant

import Hand
import card.PlayingCard

class Player(val name: String, override val hand: Hand) : Participant {
    override fun drawCards(cards: List<PlayingCard>) {
        hand.add(cards)
    }

    override fun showCardFirst(): List<PlayingCard> {
        return hand.cards
    }
}
