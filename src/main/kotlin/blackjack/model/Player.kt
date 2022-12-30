package blackjack.model

import blackjack.model.MatchResult.LOSE

class Player(
    val name: String,
    var cards: Cards = Cards()
) {
    fun addCard(card: Card) {
        check(isPickable()) { "플레이어의 점수가 이미 21을 넘었습니다" }
        cards = Cards(cards.plus(card))
    }

    fun isPickable(): Boolean {
        val score = cards.getScore()
        if (score == null) {
            return UNABLE_TO_PICK
        }

        return score < STOP_SCORE
    }

    fun getFinalScore(): Int {
        return cards.getScore() ?: FAIL_SCORE
    }

    fun wins(other: Player): MatchResult {
        val score = getFinalScore()
        val otherScore = other.getFinalScore()
        if (score == FAIL_SCORE) {
            return LOSE
        }

        return MatchResult.of(score, otherScore)
    }

    companion object {
        const val FAIL_SCORE = 0
        const val STOP_SCORE = 21
        private const val UNABLE_TO_PICK = false
    }
}
