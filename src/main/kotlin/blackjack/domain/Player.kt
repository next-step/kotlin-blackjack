package blackjack.domain

class Player(
    override val name: String,
    override var status: PlayerStatus = PlayerStatus.PLAYING,
    override val hands: Hands = Hands(),
) : Participant(name, status, hands) {
    fun toStay() {
        status = PlayerStatus.STAY
    }

    override fun handleStatus() {
        status =
            when {
                score > BLACKJACK_VALUE -> PlayerStatus.BURST
                score == BLACKJACK_VALUE -> PlayerStatus.STAY
                else -> PlayerStatus.PLAYING
            }
    }
}
