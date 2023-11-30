package blackjack.domain

enum class State {
    HIT,
    BUST,
    STAY,
    BLACKJACK;

    companion object {
        fun fromHand(hand: Hand): State {
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
    }
}
