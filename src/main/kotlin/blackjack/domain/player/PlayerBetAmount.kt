package blackjack.domain.player

data class PlayerBetAmount(val initValue: Long = INIT_BET_AMOUNT) {
    var revenue: Long = INIT_REVENUE
        private set

    fun calcRevenue(gameResult: GameResult, isBlackjack: Boolean): Long {
        val resultRevenue = when {
            isBlackjack && gameResult == GameResult.WIN -> (initValue * MULTIPLE_VALUE_OF_BLACK_JACK).toLong()
            gameResult == GameResult.WIN -> initValue
            gameResult == GameResult.LOOSE -> -initValue
            else -> TIE_REVENUE
        }
        revenue = resultRevenue
        return resultRevenue
    }

    companion object {
        const val INIT_BET_AMOUNT = 0L
        const val INIT_REVENUE = 0L
        const val TIE_REVENUE = 0L
        const val MULTIPLE_VALUE_OF_BLACK_JACK = 1.5
    }
}
