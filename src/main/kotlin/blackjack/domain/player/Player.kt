package blackjack.domain.player

class Player(
    val player: Participant,
    val betAmount: Double = 0.0,
) : BlackjackFunction by player {

    fun isCardReceiveAble(): Boolean {
        return player.getCardSum() < DEADLINE
    }

    companion object {
        private const val DEADLINE = 21
    }
}
