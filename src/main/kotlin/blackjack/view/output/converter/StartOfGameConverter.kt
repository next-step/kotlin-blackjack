package blackjack.view.output.converter

import blackjack.domain.Player

object StartOfGameConverter : OutputConverter<List<Player>> {
    private const val GUIDANCE_MESSAGE_PLAYERS = "에게 2장을 나누었습니다."

    override fun convert(printable: List<Player>): String {
        return "${printable.toNamesText()}${GUIDANCE_MESSAGE_PLAYERS}${printable.toPrintableText()}"
    }

    private fun List<Player>.toNamesText(): String {
        return joinToString(", ") { player -> player.name.value }
    }

    private fun List<Player>.toPrintableText(): String {
        return joinToString("") { player ->
            "\n${PlayerConverter.convert(player)}"
        }
    }
}
