package blackjack.domain.game

import blackjack.domain.card.ReceivedCards

object Score {

    fun calculateScore(receivedCards: ReceivedCards): Int {
        var score = receivedCards.sumOfCards()
        if (score > BLACKJACK_SCORE) {
            val aceCount = receivedCards.countOfAceCard()

            score = score - (ACE_NUMBER_TO_ELEVEN * aceCount) + (ACE_NUMBER_TO_ONE * aceCount)
        }

        return score
    }

    private const val BLACKJACK_SCORE = 21
    private const val ACE_NUMBER_TO_ONE = 1
    private const val ACE_NUMBER_TO_ELEVEN = 11
}
