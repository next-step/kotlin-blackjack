package blackjack.domain.participant

import blackjack.domain.PlayerGameResult

class Players private constructor(
    val values: List<Player>,
    private var turn: Int = 0
) {
    init {
        require(this.values.isNotEmpty()) { "플레이어가 없으면 게임을 진행할 수 없습니다." }
    }

    fun judgementGameResults(dealer: Dealer): List<PlayerGameResult> = values.map {
        PlayerGameResult(
            playerName = it.playerName,
            gameResult = it.judgementGameResult(dealer.getScore())
        )
    }

    fun getCurrentTurnPlayer(): Player = values[turn++]

    fun isNotAllFinished(): Boolean = values.any { it.isRunning() }

    companion object {
        fun of(playNameValues: List<String>, dealer: Dealer): Players =
            Players(
                values = playNameValues.map {
                    Player.of(
                        nameValue = it,
                        firstCard = dealer.drawCard(),
                        secondCard = dealer.drawCard(),
                    )
                }
            )
    }
}
