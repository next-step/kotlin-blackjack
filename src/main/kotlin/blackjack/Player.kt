package blackjack

class Player(val name: String) {
    val cardDeck = Cards()
}

class Players(private val players: List<Player>)
