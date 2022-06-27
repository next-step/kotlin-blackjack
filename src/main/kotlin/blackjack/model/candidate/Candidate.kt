package blackjack.model.candidate

import blackjack.model.card.Card
import blackjack.model.card.Cards

abstract class Candidate protected constructor(
    val candidateName: CandidateName,
    val cards: Cards = Cards(),
    var needMoreCard: Boolean = true,
) {
    val cardSize
        get() = cards.size

    val sumOfCardScore
        get() = cards.sumOfScore

    val name
        get() = candidateName.name

    open val isDealer
        get() = false

    fun isAllScoreGreaterThan(other: Int) = sumOfCardScore.isAllScoreGreaterThan(other)

    fun beats(other: Candidate, boundaryScore: Int) =
        sumOfCardScore.findNearestScoreEqualOrLessThan(boundaryScore) > other.sumOfCardScore.findNearestScoreEqualOrLessThan(
            boundaryScore
        )

    open fun receiveCard(card: Card) {
        cards.addOne(card)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Candidate) return false

        if (candidateName != other.candidateName) return false

        return true
    }

    override fun hashCode() = candidateName.hashCode()
}
