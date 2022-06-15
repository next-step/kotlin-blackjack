package blackjack.view.input.converter

import blackjack.domain.Player
import blackjack.domain.PlayerName

object PlayersConverter : InputConverter<List<Player>> {
    private const val DELIMITER = ","

    override fun convert(input: String): List<Player> {
        return input.split(DELIMITER).map { name ->
            Player(PlayerName(name.trim()))
        }
    }
}
