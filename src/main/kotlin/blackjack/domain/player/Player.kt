package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore

data class Player(
    val name: PlayerName,
    val hand: Hand = Hand(),
) {
    val isOverMaxScore: Boolean
        get() = hand.score.isOverMaxScore

    val score: HandScore
        get() = hand.score

    fun addCard(card: Card) {
        hand.add(card)
    }
}
