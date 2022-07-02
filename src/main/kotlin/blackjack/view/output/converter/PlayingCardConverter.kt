package blackjack.view.output.converter

import blackjack.domain.PlayingCard

object PlayingCardConverter : OutputConverter<PlayingCard> {
    override fun convert(printable: PlayingCard): String {
        return "${CardNumberConverter.convert(printable.number)}${SuitConverter.convert(printable.suit)}"
    }
}
