package blackjack.view

import blackjack.domain.CardSuite

object CardSuiteName {

    val CardSuite.koreaName: String
        get() {
            return when {
                this == CardSuite.HEART -> "하트"
                this == CardSuite.DIAMOND -> "다이아몬드"
                this == CardSuite.SPADE -> "스페이드"
                this == CardSuite.CLOVER -> "클로버"
                else -> ""
            }
        }
}
