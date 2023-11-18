package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

data class Player(
    val name: PlayerName,
    val hand: Hand = Hand(),
) {
    val isOverMaxScore: Boolean
        get() = hand.score.isOverMaxScore

    fun addCard(card: Card) {
        hand.add(card)
    }
}
