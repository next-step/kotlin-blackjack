package blackjack.player

object PlayerCreator {
    fun create(names: List<String>): List<Player> {
        return names.map(::Player)
    }
}
