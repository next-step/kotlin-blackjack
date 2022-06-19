package blackjack.view.output.converter

import blackjack.domain.Participant
import blackjack.domain.PlayingCards

object PlayerConverter : OutputConverter<Participant> {
    override fun convert(printable: Participant): String {
        return "${printable.name.value}카드: ${printable.hands.cards.toPrintableText()}"
    }

    private fun PlayingCards.toPrintableText(): String {
        return joinToString(", ") { playingCard ->
            "${CardNumberConverter.convert(playingCard.number)}${SuitConverter.convert(playingCard.suit)}"
        }
    }
}
