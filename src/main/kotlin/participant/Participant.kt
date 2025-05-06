package participant

import Hand
import card.PlayingCard

interface Participant {
    val hand: Hand
    val name: String

    fun drawCards(cards: List<PlayingCard>)

    fun showCardFirst(): List<PlayingCard>
}
