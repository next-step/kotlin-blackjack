package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore

interface CardPlayer {
    val hand: Hand

    val score: HandScore
        get() = HandScore.from(hand)

    val isGreaterOrEqualToMaxScore: Boolean
        get() = score.isGreaterOrEqualToMaxScore

    infix fun isSameCardCount(count: Int): Boolean = hand.cardsCount == count
    infix fun isSameScore(score: Int): Boolean = this.score.value == score

    infix fun isGreaterScoreThan(other: Int): Boolean =
        score.value > other

    fun addCard(card: Card) {
        require(score.isGreaterOrEqualToMaxScore.not()) { "최대 점수 이상이라 카드를 더 받을 수 없습니다" }
        hand.add(card)
    }

    fun hitOrStand(): Action
}
