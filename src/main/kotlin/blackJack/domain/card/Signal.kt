package blackJack.domain.card

interface Signal {
    fun isContinue(): Boolean

    companion object {
        fun changeDecision(score: Int, isContinue: Boolean = true): Signal {
            if (!isContinue) {
                return Stay
            }

            return when (score) {
                in MIN_NUMBER until MAX_NUMBER -> Hit
                MAX_NUMBER -> BlackJack
                else -> Bust
            }
        }

        private const val MIN_NUMBER = 0
        const val MAX_NUMBER = 21
    }
}
