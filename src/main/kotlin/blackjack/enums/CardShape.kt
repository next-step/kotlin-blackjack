package blackjack.enums

import kotlin.random.Random

enum class CardShape(name: String) {
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아"),
    SPADE("스페이드");

    companion object {
        private val cardShapes = values().toList()
        private val size = cardShapes.size

        fun randomShape(): CardShape {
            return cardShapes[Random.nextInt(size)]
        }
    }
}
