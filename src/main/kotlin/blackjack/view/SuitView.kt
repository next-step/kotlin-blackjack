package blackjack.view

import blackjack.model.Suit

object SuitView {
    fun toString(suit: Suit): String {
        return when (suit) {
            Suit.Spade -> "스페이드"
            Suit.Heart -> "하트"
            Suit.Diamond -> "다이아몬드"
            Suit.Club -> "클로버"
        }
    }
}
