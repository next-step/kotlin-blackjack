package blackJack.domain

abstract class Person(val name: String) {
    init {
        require(name.isNotBlank()) { "이름은 없을수 없습니다." }
    }

    val hands = Hands()

    open fun addCard(card: Card) {
        hands.add(card)
    }

    fun getTotalScore(): Int = hands.getTotalScore()

    fun isBust(): Boolean = getTotalScore() > BUST_SCORE

    companion object {
        private const val BUST_SCORE = 21
    }
}
