package blackjack.const

import blackjack.card.CardType

object BlackjackConst {
    const val BLACKJACK_STANDARD_VALUE = 21
    val CARD_NAME_MAP: Map<CardType, String> = mapOf(
        Pair(CardType.SPADE, "스페이드"),
        Pair(CardType.HEART, "하트"),
        Pair(CardType.DIAMOND, "다이아몬드"),
        Pair(CardType.CLOVER, "클로버")
    )
}
