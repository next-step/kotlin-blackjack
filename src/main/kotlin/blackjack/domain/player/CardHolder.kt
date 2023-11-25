package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore

interface CardHolder {
    val hand: Hand

    val score: HandScore
        get() = HandScore.from(hand)

    val isBust: Boolean
        get() = score.isBust

    fun isScoreGreaterThan(other: Int): Boolean =
        score.isGreaterThan(other)

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun hitOrStand(): Action
}
