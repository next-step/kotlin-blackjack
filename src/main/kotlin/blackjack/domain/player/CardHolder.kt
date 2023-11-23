package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore

interface CardHolder {
    val hand: Hand

    val score: HandScore
        get() = hand.score

    val isOverMaxScore: Boolean
        get() = hand.score.isOverMaxScore

    fun isScoreGreaterThan(other: Int): Boolean =
        this.score.isGreaterThan(other)

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun hitOrStand(): Action
}
