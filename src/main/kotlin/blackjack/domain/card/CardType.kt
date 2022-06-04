package blackjack.domain.card

import blackjack.domain.Score

sealed class CardType(val name: String, val score: Score) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardType

        if (name != other.name) return false
        if (score != other.score) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + score.hashCode()
        return result
    }
}

class Ace : CardType("에이스", Score(1)) {
    val maxScore: Score = Score(11)
}

class King : CardType("킹", Score(10))

class Queen : CardType("퀸", Score(10))

class Jack : CardType("잭", Score(10))

class NumberCard(number: Int) : CardType(number.toString(), Score(number)) {
    init {
        require(number in CARD_NUMBER_RANGE) {
            "카드 번호는 ${CARD_NUMBER_RANGE.first} 와 ${CARD_NUMBER_RANGE.last} 사이여야 합니다. (입력: $number)"
        }
    }

    companion object {
        private val CARD_NUMBER_RANGE = 2..10
    }
}
