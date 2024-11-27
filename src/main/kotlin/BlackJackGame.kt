class BlackJackGame(
    private val players: List<Player>,
) {
    private val deck: Deck = Deck()

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
}

data class DrawResult(
    val playerName: String,
    val cards: List<DrawCard>,
)
