package blackjack.domain

data class Players(private val players: List<Player>) {
    override fun toString(): String {
        return players.joinToString()
    }

    fun setUpWithCards(deck: Deck) {
        players.forEach { player -> (1 until 21).map { player.draw(deck) } }
    }

    fun findPlayer(nth: Int): Player {
        return players[nth]
    }

    fun size() = players.size

    fun stateOfCards(): String {
        return players.joinToString("/n") { "${it}카드: ${it.stateOfCards()}" }
    }
}
