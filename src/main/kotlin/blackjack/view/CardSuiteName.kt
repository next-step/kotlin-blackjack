package blackjack.view

import blackjack.domain.CardSuite

object CardSuiteName {

    val CardSuite.koreaName: String
        get() = when (this) {
            CardSuite.HEART -> "하트"
            CardSuite.DIAMOND -> "다이아몬드"
            CardSuite.SPADE -> "스페이드"
            CardSuite.CLOVER -> "클로버"
        }
}
