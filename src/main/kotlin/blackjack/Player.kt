package blackjack

class Player(val name: String) {
    val cardDeck = Cards()
}

class Players(private val _players: List<Player>) {
    val players: List<Player>
        get() = _players.toList()
}
