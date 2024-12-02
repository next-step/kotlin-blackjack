package blackjack

class BlackJackGame private constructor(
    val players: List<Player>,
    val deck: Deck,
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
            players.forEach { player -> (1..2).forEach { _ -> player.addCard(deck.getSingleCard()) } }
            return players
        }
    }
}
