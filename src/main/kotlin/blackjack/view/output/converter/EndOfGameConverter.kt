package blackjack.view.output.converter

import blackjack.domain.Participant

object EndOfGameConverter : OutputConverter<List<Participant>> {
    override fun convert(printable: List<Participant>): String {
        return printable.joinToString("\n") { player ->
            "${PlayerConverter.convert(player)} - 결과: ${player.score.value}"
        }
    }
}
