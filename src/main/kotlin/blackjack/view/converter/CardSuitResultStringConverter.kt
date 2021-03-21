package blackjack.view.converter

import blackjack.domain.CardSuit

object CardSuitResultStringConverter : ResultStringConverter<CardSuit> {
    override fun convert(t: CardSuit): String {
        return when (t) {
            CardSuit.CLUBS -> "클로버"
            CardSuit.HEARTS -> "하트"
            CardSuit.DIAMONDS -> "다이아몬드"
            CardSuit.SPADES -> "스페이드"
        }
    }
}
