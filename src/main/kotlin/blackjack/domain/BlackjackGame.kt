package blackjack.domain

class BlackjackGame(
    val players: List<Player>,
    private val deck: Deck,
) {
    fun drawCard(player: Player) {
        val newCard = deck.drawCard()
        player.addCard(newCard)
    }

    companion object {
        fun createGame(
            playerNames: List<PlayerName>,
            deck: Deck,
        ): BlackjackGame {
            val players =
                playerNames.map { name ->
                    val handCards = listOf(deck.drawCard(), deck.drawCard())
                    Player.createNew(name, handCards)
                }
            return BlackjackGame(players, deck)
        }
    }
}
