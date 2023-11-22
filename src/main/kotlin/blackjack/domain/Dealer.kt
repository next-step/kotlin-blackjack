package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore
import blackjack.domain.player.Player

class Dealer(
    val deck: Deck = Card.allShuffled(),
    val hand: Hand = Hand(),
) {
    val score: HandScore
        get() = hand.score

    fun isScoreGreaterThan(other: Int): Boolean =
        this.score.isGreaterThan(other)

    fun dealCards(player: Player, count: Int) {
        repeat(count) {
            player.addCard(drawCard())
        }
    }

    infix fun dealToSelf(count: Int) {
        repeat(count) {
            hand.add(drawCard())
        }
    }

    private fun drawCard(): Card = deck.draw()
}
