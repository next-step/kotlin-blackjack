package blackjack.domain

class Player(
    override val name: String,
    override val hands: Hands = Hands(),
    override var status: GameStatus = GameStatus.PLAYING,
) : Participant(name, hands, status) {
    fun toStay() {
        status = GameStatus.STAY
    }

    override fun handleStatus() {
        status =
            when {
                GameStatus.isBurst(score) -> GameStatus.BURST
                GameStatus.isBlackjack(score) -> GameStatus.STAY
                else -> GameStatus.PLAYING
            }
    }
}
