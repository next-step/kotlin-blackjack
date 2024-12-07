package blackjack.domain

class Player(
    override val name: String,
    override val hands: Hands = Hands(),
) : Participant(name, hands) {
    fun toStay() {
        status = PlayerStatus.STAY
    }

    override fun handleStatus() {
        status = when {
            score > BLACKJACK_VALUE -> PlayerStatus.BURST
            score == BLACKJACK_VALUE -> PlayerStatus.STAY
            else -> PlayerStatus.PLAYING
        }
    }
}
