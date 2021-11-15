package blackjack.domain

data class CardNumber(val value: Int) {
    init {
        require(value in NUMBER_RANGE) { INVALID_NUMBER_ERROR_MSG }
    }

    companion object {
        val NUMBER_RANGE = (1..13)
        private const val INVALID_NUMBER_ERROR_MSG = "유효하지 않은 번호 입니다."
    }
}
