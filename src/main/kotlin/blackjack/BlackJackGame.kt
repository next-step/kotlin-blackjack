package blackjack

class BlackJackGame(
    private val players: List<Player>,
) {
    private val deck: Deck = Deck()
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

    fun findDrawPlayer(): PlayerName {
        val startOrder = drawOrder
        val targetOrder = (startOrder..< players.size)
            .first { players[it].canDraw() }
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

    fun stopDraw(playerName: PlayerName) {
        players.find { it.name == playerName }
            ?.stopDraw()
            ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")
    }

    fun result(): List<BlackJackGameResult> =
        players.map {
            BlackJackGameResult(
                playerName = it.name.value,
                cards = it.currentCards,
                totalValue = it.totalValue(),
            )
        }
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