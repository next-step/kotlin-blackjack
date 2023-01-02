package blackjack.model

class Players(private val value: List<Player>) : List<Player> by value {
    companion object {
        private const val NAME_STRING_DELIMITER = ","

        fun of(vararg player: Player): Players {
            return Players(player.toList())
        }

        fun init(names: String, cardDeck: CardDeck, initCardCount: Int): Players {
            val splitNames = splitNames(names)
            return Players(
                splitNames.map { name -> Player.init(name, cardDeck, initCardCount) }
            )
        }

        private fun splitNames(names: String): List<String> {
            return names.split(NAME_STRING_DELIMITER).map { it.trim() }
        }
    }
}
