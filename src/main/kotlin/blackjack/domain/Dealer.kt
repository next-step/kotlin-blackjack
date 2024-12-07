package blackjack.domain

class Dealer(
    override val name: String = "딜러",
    override val hands: Hands = Hands(),
) : Participant(name, hands) {
    private val shouldDraw: Boolean = score < DEALER_DRAW_THRESHOLD

    override fun initialDraw(deck: Deck) {
        super.initialDraw(deck)

        if (shouldDraw) {
            hit(deck)
        }

        if (score >= 21) {
            status = PlayerStatus.STAY
        }
    }

    override fun handleStatus() {
        status = when {
            score > BLACKJACK_VALUE -> PlayerStatus.BURST
            score >= DEALER_DRAW_THRESHOLD -> PlayerStatus.STAY
            else -> PlayerStatus.PLAYING
        }
    }

    companion object {
        private const val DEALER_DRAW_THRESHOLD = 17
    }
}
