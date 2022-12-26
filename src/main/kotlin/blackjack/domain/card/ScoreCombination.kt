package blackjack.domain.card

import java.util.SortedSet

class ScoreCombination {
    private var combinations: SortedSet<Int> = sortedSetOf(0)

    fun update(newCard: Card) {
        val addMain = combinations.map { combination -> combination + newCard.mainScore() }
        val addSub = combinations.map { combination -> combination + newCard.secondaryScore() }

        combinations = (addMain + addSub).toSortedSet()
    }

    /**
     * @return 21 이하면서 가장 큰 수 또는 21 초과이면서 가장 작은 수
     */
    fun calculateScore(): Int {
        return combinations.lastOrNull { combination -> combination <= LIMIT_SCORE }
            ?: combinations.first { combination -> combination > LIMIT_SCORE }
    }

    /**
     * 숫자 조합 중 21이 있거나 최소값이 21을 넘었으면 true
     * 이외에 false
     */
    fun isFull(): Boolean {
        return combinations.first() >= LIMIT_SCORE ||
            combinations.any { combination -> combination == LIMIT_SCORE }
    }

    companion object {
        private const val LIMIT_SCORE = 21
    }
}
