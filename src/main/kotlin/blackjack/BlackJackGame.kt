package blackjack

class BlackJackGame private constructor(
    val players: List<Player>,
    private val deck: Deck,
) {
    fun drawSingleCardToPlayer(
        isDrawingCard: Boolean,
        player: Player,
    ): Boolean {
        return if (isDrawingCard) {
            player.addCard(deck.getSingleCard())
            true
        } else {
            false
        }
    }

    companion object {
        private const val DEFAULT_CARD_COUNT = 2

        fun createGame(
            players: List<Player>,
            deck: Deck,
        ): BlackJackGame {
            return BlackJackGame(setPlayersDefaultCards(players, deck), deck)
        }

        private fun setPlayersDefaultCards(
            players: List<Player>,
            deck: Deck,
        ): List<Player> {
            players.forEach { player -> repeat(DEFAULT_CARD_COUNT) { player.addCard(deck.getSingleCard()) } }
            return players
        }
    }
}
