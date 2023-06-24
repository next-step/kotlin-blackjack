package blackjack.domain.card

import blackjack.ErrorCode

class CardScore(val value: Int) {
    init {
        require(value in SCORE_RANGE_START..SCORE_RANGE_END) { ErrorCode.INVALID_SCORE_VALUE.message }
    }

    companion object {
        private const val SCORE_RANGE_START = 1
        private const val SCORE_RANGE_END = 11
    }
}
