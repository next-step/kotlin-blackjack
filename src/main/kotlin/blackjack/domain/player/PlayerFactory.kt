package blackjack.domain.player

class PlayerFactory {
    companion object {
        private const val DELIMITERS = ","

        fun create(input: String): List<Player> {
            return input.split(DELIMITERS).map { Player(PlayerName(it)) }
        }
    }
}
