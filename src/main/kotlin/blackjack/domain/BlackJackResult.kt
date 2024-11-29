package blackjack.domain

class BlackJackResult(
    val blackJackPlayers: BlackJackPlayers,
    val blackJackCardsMap: BlackJackCardsMap,
) {
    fun getWinPlayers(): List<BlackJackPlayer> {
        return blackJackPlayers.getWinPlayer()
    }
}
