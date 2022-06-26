package blackjack.view.output.converter

import blackjack.domain.Dealer
import blackjack.domain.Participants

object StartOfGameConverter : OutputConverter<Participants> {
    private const val GUIDANCE_MESSAGE_PLAYERS = "에게 2장을 나누었습니다."

    override fun convert(printable: Participants): String {
        return "${printable.toNamesText()}${GUIDANCE_MESSAGE_PLAYERS}${printable.toPrintableText()}"
    }

    private fun Participants.toNamesText(): String {
        val dealerName = dealer.name.value
        val playerNames = players.joinToString(", ") { player -> player.name.value }
        return "${dealerName}와 $playerNames"
    }

    private fun Participants.toPrintableText(): String {
        val dealerText = dealer.toPrintableText()
        val playersText = players.joinToString("") { player ->
            "\n${PlayerConverter.convert(player)}"
        }
        return "\n${dealerText}$playersText"
    }

    private fun Dealer.toPrintableText(): String {
        return "${name.value}: ${PlayingCardConverter.convert(getFirstCard())}"
    }
}
