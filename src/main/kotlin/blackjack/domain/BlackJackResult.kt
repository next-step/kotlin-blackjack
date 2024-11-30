package blackjack.domain

class BlackJackResult(val blackJackPlayers: BlackJackPlayers) {
    fun getWinPlayers(): List<BlackJackPlayer> {
        return blackJackPlayers.getWinPlayer()
    }
}
