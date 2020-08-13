package blackJack.domain

open class People(val name: String?) {
    private val _hands = mutableListOf<Card>()
    val hands: List<Card>
        get() = _hands.toList()

    fun addCard(card: Card) {
        _hands.add(card)
    }

    fun getTotalScore(): Int = TotalScore.getScore(_hands.map { it.number })

    fun isBust(): Boolean = getTotalScore() > 21
}
