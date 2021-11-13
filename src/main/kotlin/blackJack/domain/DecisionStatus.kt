package blackJack.domain

interface DecisionStatus {
    fun isContinue(): Boolean

    companion object {
        fun changeDecisionStatus(cards: Cards, isContinue: Boolean = true): DecisionStatus {
            if (!isContinue) {
                return Stay()
            }

            return when (cards.sumCards()) {
                in MIN_NUMBER until MAX_NUMBER -> Hit()
                BLACKJACK -> BlackJack()
                else -> Bust()
            }
        }

        private const val MIN_NUMBER = 0
        private const val MAX_NUMBER = 20
        private const val BLACKJACK = 21
    }
}
