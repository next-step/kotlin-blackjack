package blackjack.view.res

import blackjack.model.Suit

fun getString(suit: Suit) = when (suit) {
    Suit.SPADE -> "스페이드"
    Suit.HEART -> "하트"
    Suit.DIAMOND -> "다이아몬드"
    Suit.CLOVER -> "클로버"
}
