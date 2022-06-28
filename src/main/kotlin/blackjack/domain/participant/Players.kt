package blackjack.domain.participant

class Players private constructor(
    val values: List<Player>,
    private var turn: Int = 0
) {
    init {
        require(this.values.isNotEmpty()) { "플레이어가 없으면 게임을 진행할 수 없습니다." }
    }

    fun receiveInitCards(dealer: Dealer) {
        repeat(2) {
            values.forEach { player ->
                player.receiveInitCards(
                    firstCard = dealer.drawCard(),
                    secondCard = dealer.drawCard()
                )
            }
        }
    }

    fun getCurrentTurnPlayer(): Player = values[turn++]

    fun isNotAllFinished(): Boolean = values.any { it.isRunning() }

    companion object {
        fun enrollPlayers(playNameValues: List<String>): Players =
            Players(values = playNameValues.map { Player.enrollPlayer(nameValue = it) })
    }
}
