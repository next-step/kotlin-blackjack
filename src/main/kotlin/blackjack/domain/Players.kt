package blackjack.domain

data class Players(val players: List<Player>) {
    fun initCard(deck: Deck): Players {
        val newPlayers = players.map {
            it.copy(hand = Hand(deck.init()))
        }
        return Players(newPlayers)
    }

    fun getNames(): List<String> {
        return players.map {
            it.name
        }
    }

    companion object {
        fun init(names: List<String>): Players {
            return Players(players = names.map { Player(it) })
        }
    }
}
