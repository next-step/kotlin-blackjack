package blackjack.domain.game

import blackjack.domain.player.Hand

object ScoreCalculator {

    fun calculate(hand: Hand): Int {
        val sortedCards = hand.getCards().sortedBy { it.getScore() }
        return sortedCards.fold(0) { acc, card -> acc + card.getScore(acc) }
    }
}
