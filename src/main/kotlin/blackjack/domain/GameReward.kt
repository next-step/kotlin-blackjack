package blackjack.domain

sealed interface GameReward {
    fun getValue(): Double

    data class WinReward(private val amount: Amount) : GameReward {
        override fun getValue(): Double = amount.value * 1.0
    }

    data class LoseReward(private val amount: Amount) : GameReward {
        override fun getValue(): Double = amount.value * -1.0
    }

    companion object {
        private const val DEFAULT_RATIO = 1.0
        private const val BLACKJACK_RATIO = 1.5

        fun create(amount: Amount, outcome: GameOutcome, player: Player): GameReward {
            val ratio = when (player.isBlackJack()) {
                true -> BLACKJACK_RATIO
                false -> DEFAULT_RATIO
            }

            return when (outcome) {
                GameOutcome.WIN -> WinReward(amount.multiply(ratio))
                GameOutcome.LOSE -> LoseReward(amount.multiply(ratio))
            }
        }
    }
}
