package blackJack.domain

import blackJack.error.ErrorMessage

class Cards(cards: MutableList<Card>) {
    init {
        require(cards.isNotEmpty()) { ErrorMessage.EMPTY_CARDS.message }
    }

    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun drawCard(): Card {
        require(_cards.isNotEmpty()) { ErrorMessage.EMPTY_CARDS.message }
        return _cards.removeAt(0)
    }

    fun calculateTotalScore(): Int {
        val sumWithoutAces = _cards.filter { it.rank != Rank.ACE }.sumOf { it.rank.score }
        val aceCount = _cards.count { it.rank == Rank.ACE }
        val extraAceScore = Rank.ACE.otherScore + (aceCount - 1) * Rank.ACE.score

        val aceScore = if (isExtraAceScore(aceCount, sumWithoutAces, extraAceScore)) {
            extraAceScore
        } else {
            aceCount * Rank.ACE.score
        }

        return sumWithoutAces + aceScore
    }

    private fun isExtraAceScore(aceCount: Int, sumWithoutAces: Int, additionalAceScore: Int): Boolean {
        return aceCount > 0 && sumWithoutAces + additionalAceScore <= MAX_SCORE
    }

    fun addCard(card: Card) = _cards.add(card)

    companion object {
        const val MAX_SCORE = 21
    }
}
