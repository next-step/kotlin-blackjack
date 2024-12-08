package blackjack.domain

class BlackJackGame(
    private val players: List<Player>,
) {
    private val deck: Deck = Deck.randomCardDeck()
    private var drawOrder: Int = 0

    fun initialDraw(): List<DrawResult> {
        repeat(2) { _ ->
            players.forEach { it.addCard(deck.draw()) }
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
        val startOrder = drawOrder
        val targetOrder = (startOrder..< players.size)
            .firstOrNull { players[it].canDraw() }

        if (targetOrder == null) {
            return null
        }

        drawOrder = (targetOrder + 1) % players.size
        return players[targetOrder].name
    }

    fun drawCard(playerName: PlayerName): DrawResult {
        val player = players.find { it.name == playerName }
        player?.addCard(deck.draw())
            ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

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
                totalValue = it.totalValue(),
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
