package blackjack.view.output.converter

import blackjack.domain.PlayingCards

object PlayingCardsConverter : OutputConverter<PlayingCards> {
    private const val SEPARATOR = ", "

    override fun convert(printable: PlayingCards): String {
        return printable.joinToString(SEPARATOR) { playingCard ->
            "${CardNumberConverter.convert(playingCard.number)}${SuitConverter.convert(playingCard.suit)}"
        }
    }
}
