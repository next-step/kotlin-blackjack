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

    infix fun isGreaterCardScoreThan(other: Int): Boolean =
        score isGreaterCardScoreThan other

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun hitOrStand(): Action
}
