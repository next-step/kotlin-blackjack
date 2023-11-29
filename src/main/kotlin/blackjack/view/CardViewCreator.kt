package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardPattern

object CardViewCreator {
    fun convert(card: Card): String {
        return "${convertNumber(card.number)}${convertPattern(card.pattern)}"
    }

    private fun convertNumber(cardNumber: CardNumber): String {
        return cardNumber.score.value.toString()
    }

    private fun convertPattern(cardPattern: CardPattern): String {
        return when(cardPattern) {
            CardPattern.SPADE -> "스페이드"
            CardPattern.HEART -> "하트"
            CardPattern.DIAMOND -> "다이아몬드"
            CardPattern.CLOVER -> "클로버"
        }
    }
}
