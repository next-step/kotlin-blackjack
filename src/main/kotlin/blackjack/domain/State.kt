package blackjack.domain

enum class State {
    HIT,
    BUST,
    STAY,
    BLACKJACK;

    companion object {
        fun fromPlayer(hand: Hand): State {
            if (hand.getScore() > BlackjackRule.TARGET_SCORE) {
                return BUST
            }

            if (
                hand.getScore() == BlackjackRule.TARGET_SCORE &&
                hand.getCount() == BlackjackRule.INITIAL_CARD
            ) {
                return BLACKJACK
            }

            return HIT
        }

        fun fromDealer(hand: Hand): State {
            if (hand.getScore() > BlackjackRule.TARGET_SCORE) {
                return BUST
            }

            if (
                hand.getScore() == BlackjackRule.TARGET_SCORE &&
                hand.getCount() == BlackjackRule.INITIAL_CARD
            ) {
                return BLACKJACK
            }

            if (hand.getScore() < BlackjackRule.DEALER_MINIMUM_SCORE) {
                return HIT
            }

            return STAY
        }
    }
}
