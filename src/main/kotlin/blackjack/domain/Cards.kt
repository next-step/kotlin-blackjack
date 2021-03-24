package blackjack.domain

class Cards(vararg elements: Card) {
    private val _elements = elements.toMutableList()
    val elements: List<Card>
        get() = _elements.toList()

    val score: Score
        get() {
            val summedScores = _elements
                .map { it.scores() }
                .reduce { acc, scores -> acc.sumDistinct(scores) }

            return summedScores.sorted().lastOrNull { it <= Score.BLACKJACK }
                ?: summedScores.min()
                ?: throw RuntimeException("점수가 없다. _elements: $_elements, summedScores: $summedScores")
        }

    init {
        validateSize()
        validateDuplication()
    }

    fun add(card: Card) {
        _elements.add(card)
    }

    private fun validateSize() {
        require(this._elements.size == CARDS_MIN_COUNT)
    }

    private fun validateDuplication() {
        require(this._elements.distinct().count() == CARDS_MIN_COUNT)
    }

    private fun List<Score>.sumDistinct(scores: List<Score>): List<Score> {
        return this.flatMap { score ->
            scores.map { score + it }.distinct()
        }
    }

    companion object {
        private const val CARDS_MIN_COUNT = 2
    }
}
