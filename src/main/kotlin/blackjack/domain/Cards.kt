package blackjack.domain

import blackjack.domain.exception.ScoreNotExistException

data class Cards(val elements: List<Card>) {

    val score: Score
        get() {
            val summedScores = elements
                .map { it.scores() }
                .reduce { acc, scores -> acc.sumDistinct(scores) }

            return summedScores.sorted().lastOrNull { it <= Score.BLACKJACK }
                ?: summedScores.min()
                ?: throw ScoreNotExistException("점수가 없다. _elements: $elements, summedScores: $summedScores")
        }

    init {
        validateDuplication()
    }

    fun add(card: Card): Cards {
        return Cards(elements + card)
    }

    private fun validateDuplication() {
        require(this.elements.count() == this.elements.distinct().count())
    }

    private fun List<Score>.sumDistinct(scores: List<Score>): List<Score> {
        return this.flatMap { score ->
            scores.map { score + it }.distinct()
        }
    }
}
