package blackjack.domain

import blackjack.domain.Score.Companion.toScore
import kotlin.math.abs

interface CardGameScore {
    fun calculateScore(cards: Cards): Score
}

// TODO: do clean code
class BlackJackScore : CardGameScore {
    override fun calculateScore(cards: Cards): Score {
        return cards.all()
            .fold(0.toScore()) { acc, card ->
                acc + card.score()
            }.withConsiderAceCase(cards)
    }

    private fun Card.score(): Score = scoreTable.getValue(cardLevel)

    private fun Score.withConsiderAceCase(cards: Cards): Score {
        if (!cards.hasAce()) return this

        var withoutAceScore = this.withoutAceScore(cards.aceCount())
        val possibleAcePlusScores = possibleAceScores(cards.aceCount())

        var resultScore = withoutAceScore + possibleAcePlusScores.first()
        possibleAcePlusScores.forEach {
            if (calculateDiffWithPerfectScore(resultScore) > calculateDiffWithPerfectScore(withoutAceScore + it)) {
                resultScore = withoutAceScore + it
            }
        }
        return resultScore
    }

    private fun Cards.hasAce(): Boolean = aceCount() > 0

    private fun Cards.aceCount(): Int = all().filter { it.cardLevel.isAce() }.size

    private fun CardLevel.isAce(): Boolean = this == CardLevel.ACE

    private fun Score.withoutAceScore(aceCount: Int): Score {
        return this - (aceCount * scoreTable.getValue(CardLevel.ACE).score).toScore()
    }

    private fun possibleAceScores(aceCount: Int, scores: MutableList<Score> = mutableListOf()): List<Score> {
        if (aceCount <= 1) return aceValues

        val newScores = possibleAceScores(aceCount - 1, scores)
        return newScores.map { score ->
            aceValues.map { aceValue ->
                score + aceValue
            }
        }.flatten()
    }

    private fun calculateDiffWithPerfectScore(score: Score): Int = abs(score.score - PERFECT_SCORE.score)

    companion object {
        private val scoreTable: Map<CardLevel, Score> = mapOf(
            CardLevel.TWO to 2.toScore(),
            CardLevel.THREE to 3.toScore(),
            CardLevel.FOUR to 4.toScore(),
            CardLevel.FIVE to 5.toScore(),
            CardLevel.SIX to 6.toScore(),
            CardLevel.SEVEN to 7.toScore(),
            CardLevel.EIGHT to 8.toScore(),
            CardLevel.NINE to 9.toScore(),
            CardLevel.TEN to 10.toScore(),
            CardLevel.JACK to 10.toScore(),
            CardLevel.KING to 10.toScore(),
            CardLevel.QUEEN to 10.toScore(),
            CardLevel.ACE to 11.toScore(),
        )

        private val aceValues = listOf(1.toScore(), 11.toScore())

        private val PERFECT_SCORE = 21.toScore()
    }
}

@JvmInline
value class Score(val score: Int) {

    operator fun plus(other: Score): Score {
        return (score + other.score).toScore()
    }

    operator fun minus(other: Score): Score {
        return (score - other.score).toScore()
    }

    companion object {
        fun Int.toScore(): Score = Score(this)
    }
}
