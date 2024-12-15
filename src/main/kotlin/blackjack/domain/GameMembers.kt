package blackjack.domain

class GameMembers(private val players: Players, private val dealer: Dealer) {
    fun allPlayers(): Players = Players(players, dealer)

    fun playersWithoutDealer(): Players = players

    fun dealer(): Dealer = dealer
}
