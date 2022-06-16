package blackjack.view.output.converter

import blackjack.domain.Suit

object SuitConverter : OutputConverter<Suit> {
    override fun convert(printable: Suit): String {
        return when (printable) {
            Suit.SPADES -> "스페이드"
            Suit.HEARTS -> "하트"
            Suit.DIAMONDS -> "다이아몬드"
            Suit.CLUBS -> "클로버"
        }
    }
}
