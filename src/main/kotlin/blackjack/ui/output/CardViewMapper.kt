package blackjack.ui.output

import blackjack.card.Card
import blackjack.card.CardSymbol
import blackjack.card.Suit

object CardViewMapper {

    fun toView(card: Card): String {
        return card.symbol.toView() + card.suit.toView()
    }

    private fun Suit.toView(): String {
        return when (this) {
            Suit.HEART -> "하트"
            Suit.SPADE -> "스페이드"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }

    private fun CardSymbol.toView(): String {
        return when (this) {
            CardSymbol.ACE -> "A"
            CardSymbol.TWO -> "2"
            CardSymbol.THREE -> "3"
            CardSymbol.FOUR -> "4"
            CardSymbol.FIVE -> "5"
            CardSymbol.SIX -> "6"
            CardSymbol.SEVEN -> "7"
            CardSymbol.EIGHT -> "8"
            CardSymbol.NINE -> "9"
            CardSymbol.TEN -> "10"
            CardSymbol.JACK -> "J"
            CardSymbol.QUEEN -> "Q"
            CardSymbol.KING -> "K"
        }
    }
}
