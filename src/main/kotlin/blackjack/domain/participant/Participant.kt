package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Point

interface Participant {
    val name: String
    val hand: Hand
    val state: State

    fun point(): Point = hand.calculate()
    fun playable(): Boolean
    fun saidHit(): Boolean
    fun receive(card: Card)
    fun open(): Hand = hand
    fun match(dealer: Dealer): Match
}

enum class Match {
    WIN, LOSE, DRAW
}
