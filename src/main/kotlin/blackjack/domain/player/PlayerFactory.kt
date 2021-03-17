package blackjack.domain.player

class PlayerFactory {
    companion object {
        private const val DELIMITERS = ","

        fun create(input: String): Players {
            return Players(input.split(DELIMITERS).map { Player(PlayerName(it)) })
        }
    }
}
