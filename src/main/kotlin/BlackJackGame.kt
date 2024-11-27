class BlackJackGame(
    private val players: List<Player>,
) {
    private val deck: Deck = Deck()
    private val drawOrder: Int = 0

    fun initialDraw(): List<DrawResult> {
        (1..2).forEach { _ ->
            players.forEach { it.addCard(deck.draw()) }
        }

        return players
            .map {
                DrawResult(
                    playerName = it.name,
                    cards = it.currentCards,
                )
            }
    }

    fun drawPlayer(): Player = players[drawOrder]
}

data class DrawResult(
    val playerName: String,
    val cards: List<DrawCard>,
)
