package blackjack.player

object PlayerCreator {
    private const val DELIMITER = ","

    fun create(playerNames: String): List<Player> {
        val names = playerNames.split(DELIMITER)

        return names.map(::Player)
    }
}
