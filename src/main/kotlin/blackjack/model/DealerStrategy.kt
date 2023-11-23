package blackjack.model

class DealerStrategy(
    private val currentScore: Int,
) : PlayingStrategy {

    override fun isHit(): Boolean {
        return currentScore <= DEALER_PICK_THRESHOLD
    }

    companion object {
        private const val DEALER_PICK_THRESHOLD = 16
    }
}
