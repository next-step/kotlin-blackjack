package blackjack.ui.output

import blackjack.card.Card
import blackjack.card.CardSymbol
import blackjack.card.Suit

object CardViewMapper {
    private val suitBy = mapOf(
        Suit.HEART to "하트",
        Suit.SPADE to "스페이드",
        Suit.DIAMOND to "다이아몬드",
        Suit.CLUB to "클로버",
    )

    private val symbolBy = mapOf(
        CardSymbol.ACE to "A",
        CardSymbol.TWO to "2",
        CardSymbol.THREE to "3",
        CardSymbol.FOUR to "4",
        CardSymbol.FIVE to "5",
        CardSymbol.SIX to "6",
        CardSymbol.SEVEN to "7",
        CardSymbol.EIGHT to "8",
        CardSymbol.NINE to "9",
        CardSymbol.TEN to "10",
        CardSymbol.JACK to "J",
        CardSymbol.QUEEN to "Q",
        CardSymbol.KING to "K",
    )

    fun toView(card: Card): String {
        return symbolBy[card.symbol] + suitBy[card.suit]
    }
}
