package blackjack.model

interface Gambler {
    var cards: Cards
    val stopScore: Int

    fun addCard(card: Card) {
        check(isPickable()) { "카드를 추가할 수 없습니다. 점수가 ${stopScore}점을 초과합니다." }
        cards = Cards(cards.plus(card))
    }

    fun isPickable(): Boolean {
        val score = cards.getScore()
        if (score == null) {
            return UNABLE_TO_PICK
        }

        return score <= stopScore
    }

    fun wins(other: Gambler): DualResult {
        val score = getFinalScore()
        val otherScore = other.getFinalScore()
        if (score == FAIL_SCORE) {
            return DualResult.LOSE
        }

        return DualResult.of(score, otherScore)
    }

    fun getFinalScore(): Int {
        return cards.getScore() ?: FAIL_SCORE
    }

    companion object {
        private const val FAIL_SCORE = 0
        private const val UNABLE_TO_PICK = false
    }
}
