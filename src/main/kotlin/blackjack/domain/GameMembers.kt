package blackjack.domain

class GameMembers(private val players: Players, val dealer: Dealer) {
    fun allPlayersWithDealer(): Players = Players(players, dealer)
}
