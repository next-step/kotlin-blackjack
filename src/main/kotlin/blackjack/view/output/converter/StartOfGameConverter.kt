package blackjack.view.output.converter

import blackjack.domain.Participant

object StartOfGameConverter : OutputConverter<List<Participant>> {
    private const val GUIDANCE_MESSAGE_PLAYERS = "에게 2장을 나누었습니다."

    override fun convert(printable: List<Participant>): String {
        return "${printable.toNamesText()}${GUIDANCE_MESSAGE_PLAYERS}${printable.toPrintableText()}"
    }

    private fun List<Participant>.toNamesText(): String {
        return joinToString(", ") { player -> player.name.value }
    }

    private fun List<Participant>.toPrintableText(): String {
        return joinToString("") { player ->
            "\n${PlayerConverter.convert(player)}"
        }
    }
}
