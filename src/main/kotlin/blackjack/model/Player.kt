package blackjack.model

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

    companion object {
        const val FAIL_SCORE = 0
        const val STOP_SCORE = 21
        private const val UNABLE_TO_PICK = false
    }
}
