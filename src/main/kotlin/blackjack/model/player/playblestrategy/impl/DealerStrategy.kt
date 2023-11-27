package blackjack.model.player.playblestrategy.impl

import blackjack.model.player.BlackjackScore
import blackjack.model.player.playblestrategy.PlayingStrategy

data class DealerStrategy(
    private val currentScore: BlackjackScore,
) : PlayingStrategy {

    override fun isHit(): Boolean {
        return currentScore <= DEALER_PICK_THRESHOLD
    }

    companion object {
        private val DEALER_PICK_THRESHOLD = BlackjackScore(16)
    }
}
