package blackjack.view.input.converter

import blackjack.domain.PlayerName

object PlayerNamesConverter : InputConverter<List<PlayerName>> {
    private const val DELIMITER = ","

    override fun convert(input: String): List<PlayerName> {
        return input.split(DELIMITER).map { name ->
            PlayerName(name.trim())
        }
    }
}
