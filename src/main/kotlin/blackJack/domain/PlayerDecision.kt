package blackJack.domain

interface PlayerDecision {
    fun isContinue(): Boolean

    companion object {
        fun changeDecision(score: Int, isContinue: Boolean = true): PlayerDecision {
            if (!isContinue) {
                return Stay()
            }

            return when (score) {
                in MIN_NUMBER..MAX_NUMBER -> Hit()
                BLACKJACK -> BlackJack()
                else -> Bust()
            }
        }

        private const val MIN_NUMBER = 0
        private const val MAX_NUMBER = 20
        private const val BLACKJACK = 21
    }
}
