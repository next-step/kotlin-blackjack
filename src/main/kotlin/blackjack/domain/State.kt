package blackjack.domain

enum class State(val isFinished: Boolean) {
    HIT(false),
    BUST(true),
    BLACKJACK(true);

    companion object {
        private const val BLACKJACK_VALUE = 21

        fun from(hands: Hands): State {
            val sum = hands.sum()

            if (sum > BLACKJACK_VALUE) {
                return BUST
            }

            if (sum == BLACKJACK_VALUE && hands.size() == Hands.INIT_CARD_SIZE) {
                return BLACKJACK
            }

            return HIT
        }
    }
}
