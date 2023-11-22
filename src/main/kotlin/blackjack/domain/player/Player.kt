package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore

data class Player(
    val name: PlayerName,
    override val hand: Hand = Hand(),
) : CardHolder {
    override val score: HandScore
        get() = hand.score

    override val isOverMaxScore: Boolean
        get() = hand.score.isOverMaxScore

    override fun isScoreGreaterThan(other: Int): Boolean =
        this.score.isGreaterThan(other)

    override fun addCard(card: Card) {
        hand.add(card)
    }
}
