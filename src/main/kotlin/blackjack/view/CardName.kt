package blackjack.view

import blackjack.domain.Card
import blackjack.domain.enums.CardPoint
import blackjack.domain.enums.CardShape

private val pointNames = mapOf(
    CardPoint.ACE to "A",
    CardPoint.TWO to "2",
    CardPoint.THREE to "3",
    CardPoint.FOUR to "4",
    CardPoint.FIVE to "5",
    CardPoint.SIX to "6",
    CardPoint.SEVEN to "7",
    CardPoint.EIGHT to "8",
    CardPoint.NINE to "9",
    CardPoint.TEN to "10",
    CardPoint.JACK to "J",
    CardPoint.QUEEN to "Q",
    CardPoint.KING to "K",
)

private val shapeNames = mapOf(
    CardShape.HEART to "하트",
    CardShape.CLOVER to "클로버",
    CardShape.DIAMOND to "다이아",
    CardShape.SPADE to "스페이드",
)

fun Card.getName(): String {
    return "${pointNames[this.point]}${shapeNames[this.shape]}"
}
