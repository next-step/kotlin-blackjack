package blackjack.domain

data class Players(val players: List<Player>) {
    fun initCard(deck: Deck) {
        players.forEach {
            it.init(deck)
        }
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
