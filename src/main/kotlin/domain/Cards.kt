package domain

class Cards {
    private val mutableCards = mutableListOf<Card>()

    val cards: List<Card>
        get() = mutableCards.toList()

    fun add(card: Card) {
        mutableCards.add(card)
    }

    fun bestScore(): Int {
        return optimizeScore(mutableCards.sumOf { it + 0 })
    }

    private fun optimizeScore(defaultScore: Int): Int {
        var baseScore = defaultScore
        repeat(aceNumber()) {
            val anotherScore = defaultScore + CardNumber.ACE.score.secondScore!!
            if (anotherScore > Score.MAX_SCORE) {
                return baseScore
            }
            baseScore = anotherScore
        }
        return baseScore
    }

    private fun aceNumber() = mutableCards.count { it.number == CardNumber.ACE }
}
