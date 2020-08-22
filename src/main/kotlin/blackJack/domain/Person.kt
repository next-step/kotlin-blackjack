package blackJack.domain

abstract class Person(val name: String) {
    init {
        require(name.isNotBlank()) { "이름은 없을수 없습니다." }
    }

    private val _hands = mutableListOf<Card>()
    val hands: List<Card>
        get() = _hands.toList()

    fun addCard(card: Card) {
        _hands.add(card)
    }

    fun getTotalScore(cards: List<Card> = _hands): Int = TotalScore.getScore(cards)

    fun isBust(totalScore: Int = getTotalScore()): Boolean = totalScore > BUST_SCORE

    fun getHandsSize(): Int = _hands.size

    override fun toString(): String {
        return name
    }

    companion object {
        private const val BUST_SCORE = 21
    }
}
