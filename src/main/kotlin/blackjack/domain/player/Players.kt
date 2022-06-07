package blackjack.domain.player


class Players(playerNames: List<String>, dealer: Dealer) {
    val players: List<Player>

    init {
        players = playerNames.map(::Player).toMutableList()
        players.add(dealer)
    }
}
