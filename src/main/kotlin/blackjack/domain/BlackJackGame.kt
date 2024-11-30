package blackjack.domain

class BlackJackGame(
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
        ): BlackJackGame {
            val players =
                playerNames.map { name ->
                    val handCards = listOf(deck.drawCard(), deck.drawCard())
                    Player.createNew(name, handCards)
                }
            return BlackJackGame(players, deck)
        }
    }
}
