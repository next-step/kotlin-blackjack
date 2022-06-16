package blackjack.view.output.converter

import blackjack.domain.Player

object EndOfGameConverter : OutputConverter<List<Player>> {
    override fun convert(printable: List<Player>): String {
        return printable.joinToString("\n") { player ->
            "${PlayerConverter.convert(player)} - 결과: ${player.score.value}"
        }
    }
}
