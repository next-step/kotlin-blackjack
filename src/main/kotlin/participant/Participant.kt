package participant

import Hand
import card.PlayingCard

interface Participant {
    val hand: Hand

    fun drawCards(cards: List<PlayingCard>)

    fun showCardFirst(): List<PlayingCard>
}
