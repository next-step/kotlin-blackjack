package blackjack.ui

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol

object OutViewConstMap {
    private val CARD_NUMBER_MAP: Map<CardNumber, String> = mapOf(
        CardNumber.ACE to "1",
        CardNumber.TWO to "2",
        CardNumber.THREE to "3",
        CardNumber.FOUR to "4",
        CardNumber.FIVE to "5",
        CardNumber.SIX to "6",
        CardNumber.SEVEN to "7",
        CardNumber.EIGHT to "8",
        CardNumber.NINE to "9",
        CardNumber.TEN to "10",
        CardNumber.JACK to "J",
        CardNumber.QUEEN to "Q",
        CardNumber.KING to "K",
    )

    private val CARD_SYMBOL_MAP: Map<CardSymbol, String> = mapOf(
        CardSymbol.CLUB to "클로바",
        CardSymbol.DIAMOND to "다이아",
        CardSymbol.SPADE to "스페이드",
        CardSymbol.HEART to "하트",
    )

    operator fun get(value: CardNumber) = CARD_NUMBER_MAP[value]
        ?: throw IllegalArgumentException("표현 상수를 지원하지 않는 타입입니다. cardNumber = $value")

    operator fun get(value: CardSymbol) = CARD_SYMBOL_MAP[value]
        ?: throw IllegalArgumentException("표현 상수를 지원하지 않는 타입입니다. cardSymbol = $value")
}
