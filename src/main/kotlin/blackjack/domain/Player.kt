package blackjack.domain

class Player(
    override val name: String,
    override val hands: Hands = Hands(),
    override var status: PlayerStatus = PlayerStatus.PLAYING,
) : Participant(
    name = name,
    hands = hands,
    status = status,
) {
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
