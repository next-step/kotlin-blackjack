package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

data class Player(
    val name: PlayerName,
    val hand: Hand = Hand(),
) {
    fun addCard(card: Card) {
        hand.add(card)
    }
}
