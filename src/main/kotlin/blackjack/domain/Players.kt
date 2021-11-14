package blackjack.domain

data class Players(val players: List<Player>) : List<Player> by players {

    fun receiveCardFromDeck(deck: Deck): Players {
        return Players(players.map { it.receiveCard(deck.drawCard()) })
    }

    init {
        require(players.size >= MINIMUM_GAMER)
    }

    companion object {
        private const val MINIMUM_GAMER = 2

        fun createGamers(names: Names): Players {
            return Players(names.names.map { Gamer(it) })
        }
    }
}
