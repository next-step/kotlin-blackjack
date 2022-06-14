package blackjack.domain.enums

import blackjack.domain.Card

enum class CardPoint(val point: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    JACK(10),
    QUEEN(10),
    KING(10);

    companion object {
        fun point(card: Card): Int {
            return values().find { it == card.point }?.point ?: throw IllegalArgumentException("비 정상적인 카드입니다.")
        }
    }
}
