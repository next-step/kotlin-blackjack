package blackjack.domain

class BlackJackGame(
    private val players: List<Participant>,
) {
    private val drawSupporter = DrawSupporter()

    fun initialDraw(): List<DrawResult> {
        repeat(2) { _ ->
            players.forEach { it.addCard(drawSupporter.drawCard()) }
        }

        return players
            .map {
                DrawResult(
                    playerName = it.name.value,
                    cards = it.currentCards,
                )
            }
    }

    fun canDrawForAllPlayers(): Boolean = players.any { it.canDraw() }

    fun findDrawPlayer(): PlayerName? {
        val startOrder = drawSupporter.currentDrawOrder
        val targetOrder = (startOrder..< players.size)
            .firstOrNull { players[it].canDraw() }

        if (targetOrder == null) {
            return null
        }

        drawSupporter.incrementDrawOrder(targetOrder, players.size)
        return players[targetOrder].name
    }

    fun drawCard(playerName: PlayerName): DrawResult {
        val player = players.find { it.name == playerName }
            ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

        player.addCard(drawSupporter.drawCard())
        return DrawResult(
            playerName = playerName.value,
            cards = player.currentCards,
        )
    }

    fun stopDraw(playerName: PlayerName): DrawResult {
        val player = players.find { it.name == playerName }
        player
            ?.stopDraw()
            ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

        return DrawResult(
            playerName = playerName.value,
            cards = player.currentCards,
        )
    }

    fun result(): List<BlackJackGameResult> {
        check(isAllPlayerStopDraw()) { "모든 플레이어의 턴이 종료되지 않았습니다."}

        return players.map {
            BlackJackGameResult(
                playerName = it.name.value,
                cards = it.currentCards,
                totalValue = it.totalCardScore(),
            )
        }
    }

    private fun isAllPlayerStopDraw(): Boolean = players.all { !it.canDraw() }
}

data class DrawResult(
    val playerName: String,
    val cards: List<DrawCard>,
)

data class BlackJackGameResult(
    val playerName: String,
    val cards: List<DrawCard>,
    val totalValue: Int,
)
