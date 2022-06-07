package blackjack.view.input.converter

import blackjack.domain.Player

object PlayersConverter : InputConverter<List<Player>> {
    private const val DELIMITER = ","

    override fun convert(input: String): List<Player> {
        return input.split(DELIMITER).map { name ->
            Player.from(name.trim())
        }
    }
}
