package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore

interface CardPlayer {
    val hand: Hand

    val score: HandScore
        get() = HandScore.from(hand)

    val isBust: Boolean
        get() = score.isBust

    infix fun isGreaterCardScoreThan(other: Int): Boolean =
        score isGreaterCardScoreThan other

    fun addCard(card: Card) {
        require(!isBust) { "버스트라 카드를 더 받을 수 없습니다" }
        hand.add(card)
    }

    fun hitOrStand(): Action
}
