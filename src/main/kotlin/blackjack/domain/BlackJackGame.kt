package blackjack.domain

class BlackJackGame(
    val blackJackPlayers: BlackJackPlayers,
    val blackJackCardsMap: BlackJackCardsMap,
) {
    fun getWinPlayers(): List<BlackJackPlayer> {
        return blackJackPlayers.getWinPlayer()
    }
}
