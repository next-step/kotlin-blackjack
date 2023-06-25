package blackjack.domain

data class Card(val shape: String, val char: String) {
    companion object {
        private val shapeList = listOf("스페이드", "하트", "클로버", "다이아몬드")
        private val charList = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "k")
        fun draw(): Card {
            return Card(shapeList.shuffled().first(), charList.shuffled().first())
        }
    }
}
