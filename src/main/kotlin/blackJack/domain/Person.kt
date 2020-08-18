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

    fun getTotalScore(): Int = TotalScore.getScore(_hands)

    fun isBust(): Boolean = getTotalScore() > BUST_SCORE

    fun getHandsSize(): Int = _hands.size

    override fun toString(): String {
        return name
    }

    companion object {
        private const val BUST_SCORE = 21
    }
}
