package blackjack.domain

class BlackJackGame(
    private val dealer: Dealer = Dealer(),
    private val players: List<Player>
) {
    fun handOutDefaultCardToPlayers(): List<Player> {
        return players.map { player ->
            player.receiveCards(dealer.dealInitialCard())
        }
    }

    fun handOutAdditionalCard(player: Player): Player {
        return player.receiveCard(dealer.dealCard())
    }

    fun getGameResult(): Map<Player, Int> {
        return GameResult(players).scoreMap
    }
}