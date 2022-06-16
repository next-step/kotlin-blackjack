package blackjack.view.output.converter

import blackjack.domain.CardNumber

object CardNumberConverter : OutputConverter<CardNumber> {
    override fun convert(printable: CardNumber): String {
        return when (printable) {
            CardNumber.ACE -> "A"
            CardNumber.TWO -> "2"
            CardNumber.THREE -> "3"
            CardNumber.FOUR -> "4"
            CardNumber.FIVE -> "5"
            CardNumber.SIX -> "6"
            CardNumber.SEVEN -> "7"
            CardNumber.EIGHT -> "8"
            CardNumber.NINE -> "9"
            CardNumber.TEN -> "10"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
        }
    }
}
