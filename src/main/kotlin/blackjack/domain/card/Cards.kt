package blackjack.domain.card

import blackjack.domain.ScoreOverFlowException

class Cards {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    private val scoreCombination = ScoreCombination()

    fun add(newCard: Card) {
        if (scoreCombination.isFUll()) throw ScoreOverFlowException()

        _cards.add(newCard)
        scoreCombination.update(newCard)
    }

    fun getScore() = scoreCombination.calculateScore()

    fun isFull() = scoreCombination.isFUll()
}
