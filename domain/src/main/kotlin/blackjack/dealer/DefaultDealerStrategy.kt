package blackjack.dealer

import action.BlackJackAction
import blackjack.card.Card
import blackjack.card.CardRank
import blackjack.deck.Deck
import blackjack.hand.Hand

internal class DefaultDealerStrategy : DealerStrategy {
    override fun decideAction(hand: Hand, deck: Deck): BlackJackAction {
        val dealerScore = hand.calculateBestValue()
        val dealerMinScore = hand.calculateMinValue()

        val bustingProbability = maxOf(
            calculateProbabilityOfBusting(dealerScore, deck),
            calculateProbabilityOfBusting(dealerMinScore, deck)
        )

        return if (bustingProbability > 0.5) BlackJackAction.STAND else BlackJackAction.HIT
    }

    private fun calculateProbabilityOfBusting(currentScore: Int, deck: Deck): Double {
        val remainedScore = 21 - currentScore
        val safeCards = deck.remainingCards.count { isSafe(it, remainedScore) }

        return 1.0 - safeCards.toDouble() / deck.size.toDouble()
    }

    private fun isSafe(card: Card, remainedScore: Int): Boolean {
        val cardValue = when (card.rank) {
            CardRank.KING, CardRank.QUEEN, CardRank.JACK -> 10
            CardRank.ACE -> 11
            else -> card.rank.ordinal + 1
        }
        return cardValue <= remainedScore
    }
}
