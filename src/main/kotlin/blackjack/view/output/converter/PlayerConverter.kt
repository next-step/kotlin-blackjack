package blackjack.view.output.converter

import blackjack.domain.Player
import blackjack.domain.PlayingCards

object PlayerConverter : OutputConverter<Player> {
    override fun convert(printable: Player): String {
        return "${printable.name.value}카드: ${printable.cardsOfHands.toPrintableText()}"
    }

    private fun PlayingCards.toPrintableText(): String {
        return joinToString(", ") { playingCard ->
            "${CardNumberConverter.convert(playingCard.number)}${SuitConverter.convert(playingCard.suit)}"
        }
    }
}
