package blackjack.domain

class Dealer(
    override val name: String = "딜러",
    override val hands: Hands = Hands(),
    override var status: PlayerStatus = PlayerStatus.PLAYING,
) : Participant(
    name = name,
    hands = hands,
    status = status,
) {
    private val shouldDraw: Boolean = score < DEALER_DRAW_THRESHOLD

    infix fun vs(player: Player): Result {
        return when {
            status == PlayerStatus.BURST -> Result.LOSE
            player.status == PlayerStatus.BURST -> Result.WIN
            score > player.score -> Result.WIN
            score < player.score -> Result.LOSE
            else -> Result.DRAW
        }
    }

    override fun initialDraw(deck: Deck) {
        super.initialDraw(deck)

        if (shouldDraw) {
            hit(deck)
        }

        handleStatus()
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
