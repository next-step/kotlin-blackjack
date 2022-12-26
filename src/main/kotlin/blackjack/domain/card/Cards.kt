package blackjack.domain.card

import blackjack.domain.ScoreOverFlowException

class Cards {
    private val cards: MutableList<Card> = mutableListOf()
    private val scoreCombination: ScoreCombination = ScoreCombination()

    fun getCards() = cards.toList()

    fun add(newCard: Card) {
        if (scoreCombination.isFull()) throw ScoreOverFlowException()

        cards.add(newCard)
        scoreCombination.update(newCard)
    }

    fun getScore() = scoreCombination.calculateScore()

    fun isFull() = scoreCombination.isFull()

    fun isBlackJack(): Boolean {
        return cards.size == BLACKJACK_CARD_COUNT && scoreCombination.calculateScore() == BLACKJACK_SCORE
    }

    companion object {
        private const val BLACKJACK_CARD_COUNT = 2
        private const val BLACKJACK_SCORE = 21
    }
}
