package blackjack.domain

enum class State {
    HIT,
    BUST,
    STAY,
    BLACKJACK;

    companion object {
        fun fromPlayer(hand: Hand): State {
            val playerScore = hand.getScore()
            val countOfCard = hand.getCount()

            return when {
                playerScore > BlackjackRule.TARGET_SCORE -> BUST
                playerScore == BlackjackRule.TARGET_SCORE &&
                    countOfCard == BlackjackRule.INITIAL_CARD -> BLACKJACK

                else -> HIT
            }
        }

        fun fromDealer(hand: Hand): State {
            val dealerScore = hand.getScore()
            val countOfCard = hand.getCount()

            return when {
                dealerScore > BlackjackRule.TARGET_SCORE -> BUST
                dealerScore == BlackjackRule.TARGET_SCORE &&
                    countOfCard == BlackjackRule.INITIAL_CARD -> BLACKJACK

                dealerScore < BlackjackRule.DEALER_MINIMUM_SCORE -> HIT
                else -> STAY
            }
        }
    }
}
