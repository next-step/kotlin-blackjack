package blackJack.domain

import blackJack.error.ErrorMessage

class Cards(val cards: MutableList<Card>) {
    init {
        require(cards.isNotEmpty()) { ErrorMessage.EMPTY_CARDS.message }
    }

    fun drawCard(): Card {
        require(cards.isNotEmpty()) { ErrorMessage.EMPTY_CARDS.message }
        return cards.removeAt(0)
    }

    fun calculateTotalScore(): Int {
        val sumWithoutAces = cards.filter { it.rank != Rank.ACE }.sumOf { it.rank.score }
        val aceCount = cards.count { it.rank == Rank.ACE }
        // 하나의 ACE를 11로 계산했을 때의 추가 점수
        val additionalAceScore = Rank.ACE.otherScore + (aceCount - 1) * Rank.ACE.score

        val aceScore = if (aceCount > 0 && sumWithoutAces + additionalAceScore <= MAX_SCORE) {
            additionalAceScore
        } else {
            aceCount * Rank.ACE.score
        }

        return sumWithoutAces + aceScore
    }

    fun addCard(card: Card) = cards.add(card)

    companion object {
        const val MAX_SCORE = 21
    }
}
