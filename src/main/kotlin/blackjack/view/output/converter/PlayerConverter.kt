package blackjack.view.output.converter

import blackjack.domain.Hands
import blackjack.domain.Player

object PlayerConverter : OutputConverter<Player> {
    override fun convert(printable: Player): String {
        return "${printable.name}카드: ${printable.hands.toPrintableText()}"
    }

    private fun Hands.toPrintableText(): String {
        return cards.joinToString(", ") { playingCard ->
            "${playingCard.number.text}${playingCard.suit.text}"
        }
    }
}
