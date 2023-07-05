package blackjack.domain

const val BLACK_JACK = 21

class BlackJackGame(
    private val dealer: Dealer = Dealer(),
    players: Players
) {
    var players: Players = players
        private set

    fun handOutDefaultCardToPlayers() {
        players = players.receiveCards(dealer)
    }

    fun handOutAdditionalCardTo(player: Player) {
        player.receiveCard(dealer.dealCard())
    }

    fun getGameResult(): Map<Player, Int> {
        return GameResult(players).scoreMap
    }
}
