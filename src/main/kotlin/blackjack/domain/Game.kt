package blackjack.domain

class Game(private val deck: Deck, private val players: Players) {
    fun start() {
        players.distributeInitialCards(deck)
    }

    fun hit(playerName: String) {
        players.hit(playerName, deck)
    }

    fun calculateResults(): Map<String, Int> {
        return players.getPlayers().associate { it.name to it.getTotalValue() }
    }
}
