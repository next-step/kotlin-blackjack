package blackJack.domain

interface PlayerDecision {
    fun isContinue(): Boolean

    companion object {
        fun changeDecision(cards: Cards, isContinue: Boolean = true): PlayerDecision {
            if (!isContinue) {
                return Stay()
            }

            return when (cards.sumCards()) {
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
