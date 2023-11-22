package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore

interface CardHolder {
    val hand: Hand

    val score: HandScore

    val isOverMaxScore: Boolean

    fun isScoreGreaterThan(other: Int): Boolean

    fun addCard(card: Card)
}
