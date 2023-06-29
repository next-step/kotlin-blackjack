package blackjack.domain

import blackjack.domain.enums.Condition
import blackjack.domain.enums.MatchResult

open class Participant(
    val name: String,
    val cards: Cards,
    protected open val condition: Condition
) {

    open fun hit(card: Card) {
        cards.append(card)
    }

    open fun determineResult(otherScore: Score): MatchResult {
        val score = cards.calculateScore()

        return when {
            score.value > otherScore.value -> MatchResult.WIN
            score.value < otherScore.value -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }
}
