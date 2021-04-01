package blackjack.domain.card

data class Score(val value: Int) {
    val isBlackjack: Boolean = value == 21
    val isBust: Boolean = value > 21

    fun plusTenIfNotBust(): Score {
        val result = Score(value + TEN)
        return if(result.isBust) this else result
    }

    companion object {
        private const val TEN: Int = 10
        const val BLACKJACK_SCORE = 21
    }
}
