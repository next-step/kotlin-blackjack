package com.nextstep.jngcii.blackjack.domain

data class Card(val shape: Shape, val symbol: SYMBOL) {
    enum class Shape {
        CLOVER, HEART, DIAMOND, SPADE
    }
    enum class SYMBOL(val value: Int) {
        ACE(11),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);
    }
}
