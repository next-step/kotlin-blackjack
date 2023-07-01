package blackjack.domain

class BlackJackGame(
    private val dealer: Dealer = Dealer(),
    players: Players
) {
    var players: Players = players
        private set

    fun handOutDefaultCardToPlayers(): Players {
        players = players.receiveCards(dealer)
        return players
    }

    fun handOutAdditionalCard(player: Player): Player {
        return player.receiveCard(dealer.dealCard())
    }

    fun getGameResult(): Map<Player, Int> {
        return GameResult(players).scoreMap
    }
}