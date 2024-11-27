class BlackJackGame(
    private val players: List<Player>,
) {
    private var drawOrder = 0
}

data class DrawResult(
    val playerName: String,
    val cards: List<DrawCard>,
)
