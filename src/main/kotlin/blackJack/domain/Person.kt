package blackJack.domain

abstract class Person(val name: String) {
    private val _hands = mutableListOf<Card>()
    val hands: List<Card>
        get() = _hands.toList()

    fun addCard(card: Card) {
        _hands.add(card)
    }

    fun getTotalScore(): Int = TotalScore.getScore(_hands.map { it.denomination.number })

    fun isBust(): Boolean = getTotalScore() > BUST_SCORE

    companion object {
        private const val BUST_SCORE = 21
    }
}
