package blackjack.domain

data class Players(private val players: List<Player> = listOf()) : List<Player> by players {

    fun eachAcceptCards(cardDeck: CardDeck) {
        players.forEach {
            it.hit(cardDeck.next())
        }
    }

    companion object {
        fun getPlayerListByNames(names: List<String>): List<Player> {
            return names.map { Player.of(Name.from(it)) }
        }

        fun of(vararg player: Player): Players {
            return Players(listOf(*player))
        }
    }
}
