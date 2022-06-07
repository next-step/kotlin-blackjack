package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardType

data class Player(
    private val _name: String,
    val receivedCards: MutableSet<Card> = mutableSetOf()
) {

    val score: Int
        get() = calculateScore()

    val name: String
        get() = _name

    fun calculateScore(): Int {
        var score = receivedCards.sumOf { it.number }

        if (score > BLACKJACK_SCORE) {
            val aceCount = receivedCards.count {
                it.cardType == CardType.ACE
            }

            score = score - (ACE_NUMBER_TO_ELEVEN * aceCount) + (ACE_NUMBER_TO_ONE * aceCount)
        }

        return score
    }

    fun canMoreGame(): Boolean {
        return calculateScore() != BLACKJACK_SCORE && calculateScore() < BLACKJACK_SCORE
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_NUMBER_TO_ONE = 1
        private const val ACE_NUMBER_TO_ELEVEN = 11
    }
}
