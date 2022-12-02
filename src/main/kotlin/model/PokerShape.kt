package model

class PokerShape private constructor(val value: String) {

    companion object {
        private val pokerShapes: List<String> =
            listOf("하트", "클로버", "스페이드", "다이아")

        fun pokerShapes(): List<PokerShape> {
            return pokerShapes.map { PokerShape(it) }.toList()
        }
    }
}
