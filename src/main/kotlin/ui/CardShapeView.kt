package ui

import domain.CardShape

enum class CardShapeView(val cardShape: CardShape, val ui: String) {

    CLOVER(CardShape.CLOVER, "클로버"),
    HEART(CardShape.HEART, "하트"),
    SPACE(CardShape.SPACE, "스페이드"),
    DIAMOND(CardShape.DIAMOND, "다이아");

    companion object {
        fun valueOf(cardShape: CardShape) = values().single { it.cardShape == cardShape }.ui
    }
}
